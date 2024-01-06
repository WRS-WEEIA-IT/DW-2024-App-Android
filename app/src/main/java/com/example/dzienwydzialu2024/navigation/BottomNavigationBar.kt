package com.example.dzienwydzialu2024.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.dzienwydzialu2024.ui.theme.DarkBlack

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val bottomNavItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Events,
        BottomNavItem.Tasks,
        BottomNavItem.Info
    )

    BottomNavigation(
        backgroundColor = DarkBlack
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        bottomNavItems.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                icon = {
                    Icon(painter = painterResource(id = item.icon), contentDescription = item.title)
                },
                unselectedContentColor = Color.White,
                selectedContentColor = Color.Magenta,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { screenRoute ->
                            popUpTo(screenRoute) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}