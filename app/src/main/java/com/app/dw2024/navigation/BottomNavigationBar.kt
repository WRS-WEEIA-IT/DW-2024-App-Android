package com.app.dw2024.navigation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.app.dw2024.ui.theme.DarkBlack
import com.app.dw2024.ui.theme.DeepPurple

@Composable
fun BottomNavigationBar(
    navController: NavController,
    onQrCodeScannerClick: () -> Unit = {}
) {
    val bottomNavItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Events,
        BottomNavItem.QrCodeScanner,
        BottomNavItem.Tasks,
        BottomNavItem.Info
    )

    BottomNavigation(
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth(),
        backgroundColor = DarkBlack
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        bottomNavItems.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                icon = {
                    if (item.route == "qr_code_scanner") {
                        Box(
                            modifier = Modifier
                                .size(54.dp)
                                .clip(shape = CircleShape)
                                .background(DeepPurple),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(painter = painterResource(id = item.icon), contentDescription = item.title)
                        }
                    } else {
                        Icon(painter = painterResource(id = item.icon), contentDescription = item.title)
                    }
                },
                unselectedContentColor = Color.White,
                selectedContentColor = DeepPurple,
                onClick = {
                    if (currentRoute == item.route) {
                        return@BottomNavigationItem
                    }
                    if (item.route == "qr_code_scanner") {
                        onQrCodeScannerClick()
                        return@BottomNavigationItem
                    }
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { screenRoute ->
                            popUpTo(screenRoute) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                    }
                },
            )
        }
    }
}