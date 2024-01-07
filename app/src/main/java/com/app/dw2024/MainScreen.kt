package com.app.dw2024

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.app.dw2024.navigation.BottomNavItem
import com.app.dw2024.navigation.BottomNavigationBar
import com.app.dw2024.navigation.NavigationGraph
import com.app.dw2024.ui.theme.DarkBlack

@Composable
fun MainScreen(
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkBlack)
                    .padding(vertical = 16.dp),
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
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_flag),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }
        },
        bottomBar = {
            BottomAppBar(
                backgroundColor = DarkBlack,
                elevation = 0.dp,
            ) {
                BottomNavigationBar(navController = navController)
            }
        },
    ) {
        NavigationGraph(
            navController = navController,
            paddingValues = it
        )
    }
}