package com.app.dw2024.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.dw2024.MainViewModel
import com.app.dw2024.events.EventsScreen
import com.app.dw2024.home.HomeScreen
import com.app.dw2024.info.InfoScreen
import com.app.dw2024.tasks.TasksScreen
import com.app.dw2024.ui.theme.DarkBlack

@Composable
fun NavigationGraph(
    navController:  NavHostController,
    paddingValues: PaddingValues,
    mainViewModel: MainViewModel
) {
    Scaffold(
        modifier = Modifier.background(DarkBlack)
    ) {
        NavHost(
            modifier = Modifier.background(DarkBlack).padding(it),
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            enterTransition = {
                EnterTransition.None
            },
            exitTransition = {
                ExitTransition.None
            }
        ) {
            composable(BottomNavItem.Home.route) {
                HomeScreen(
                    navController = navController,
                    modifier = Modifier.padding(paddingValues),
                    mainViewModel = mainViewModel
                )
            }
            composable(BottomNavItem.Events.route) {
                EventsScreen(
                    modifier = Modifier.padding(paddingValues),
                    mainViewModel = mainViewModel
                )
            }
            composable(BottomNavItem.Tasks.route) {
                TasksScreen(modifier = Modifier.padding(paddingValues))
            }
            composable(BottomNavItem.Info.route) {
                InfoScreen(modifier = Modifier.padding(paddingValues))
            }
        }
    }
}