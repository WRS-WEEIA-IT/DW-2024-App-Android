package com.app.dw2024.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.dw2024.events.EventsScreen
import com.app.dw2024.home.HomeScreen
import com.app.dw2024.info.InfoScreen
import com.app.dw2024.tasks.TasksScreen
import com.app.dw2024.ui.theme.DarkBlack

@Composable
fun NavigationGraph(
    navController:  NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        modifier = Modifier.background(DarkBlack),
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
                modifier = Modifier.padding(paddingValues)
            )
        }
        composable(BottomNavItem.Events.route) {
            EventsScreen(modifier = Modifier.padding(paddingValues))
        }
        composable(BottomNavItem.Tasks.route) {
            TasksScreen(modifier = Modifier.padding(paddingValues))
        }
        composable(BottomNavItem.Info.route) {
            InfoScreen(modifier = Modifier.padding(paddingValues))
        }
    }
}