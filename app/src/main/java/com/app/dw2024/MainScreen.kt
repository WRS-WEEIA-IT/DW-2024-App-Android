package com.app.dw2024

import android.content.Context
import android.content.Intent
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.app.dw2024.navigation.BottomNavItem
import com.app.dw2024.navigation.BottomNavigationBar
import com.app.dw2024.navigation.NavigationGraph
import com.app.dw2024.ui.theme.DarkBlack
import com.app.dw2024.ui.theme.DarkGrey

@Composable
fun MainScreen(
    navController: NavHostController,
    onQrCodeScannerClick: () -> Unit = {},
    mainViewModel: MainViewModel
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val vibrator = LocalContext.current.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    val currentRoute = navBackStackEntry?.destination?.route
    val context = LocalContext.current
    val screensWithTopBar = listOf(
        BottomNavItem.Events.route,
        BottomNavItem.Tasks.route,
        BottomNavItem.Info.route
    )
    val successfulVibrationEffect = VibrationEffect.createOneShot(800, VibrationEffect.DEFAULT_AMPLITUDE)
    val failureVibrationEffect = VibrationEffect.createWaveform(
        longArrayOf(0, 400, 200, 400),
        -1
    )

    LaunchedEffect(key1 = true) {
        mainViewModel.mainChannel.collect { event ->
            when (event) {
                is MainEvent.OnNoQrCodeDetected -> {
                    // do nothing
                }
                is MainEvent.OnSuccessfulTaskCompletion -> {
                    vibrator.cancel()
                    vibrator.vibrate(successfulVibrationEffect)
                    mainViewModel.forceTasksToRefresh()
                    navController.navigate(BottomNavItem.Tasks.route) {
                        launchSingleTop = true
                        popUpTo(BottomNavItem.Events.route) {
                            inclusive = true
                        }
                    }
                }
                is MainEvent.OnTaskAlreadyFinished -> {
                    vibrator.cancel()
                    vibrator.vibrate(failureVibrationEffect)
                    Toast.makeText(context, "Task already finished", Toast.LENGTH_SHORT).show()
                }
                is MainEvent.OnNoSuchTaskExists -> {
                    vibrator.cancel()
                    vibrator.vibrate(failureVibrationEffect)
                    Toast.makeText(context, "You don't have such task", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            if (screensWithTopBar.contains(currentRoute)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DarkGrey)
                        .padding(vertical = 16.dp, horizontal = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_logo),
                        contentDescription = "Logo",
                        modifier = Modifier.height(76.dp)
                    )
                    if (currentRoute == BottomNavItem.Info.route) {
                        IconButton(
                            modifier = Modifier.align(Alignment.CenterEnd),
                            onClick = { context.sendMail(arrayOf("dzien.weeia@samorzad.p.lodz.pl"), "") }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Email,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        },
        bottomBar = {
            BottomAppBar(
                backgroundColor = DarkBlack,
                elevation = 0.dp,
            ) {
                BottomNavigationBar(
                    navController = navController,
                    onQrCodeScannerClick = onQrCodeScannerClick
                )
            }
        },
    ) {
        NavigationGraph(
            navController = navController,
            paddingValues = it,
            mainViewModel = mainViewModel
        )
    }
}

fun Context.sendMail(to: Array<String>, subject: String) {
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "vnd.android.cursor.item/email"
    intent.putExtra(Intent.EXTRA_EMAIL, to)
    intent.putExtra(Intent.EXTRA_SUBJECT, subject)
    startActivity(Intent.createChooser(intent, "Choose an email client"))
}