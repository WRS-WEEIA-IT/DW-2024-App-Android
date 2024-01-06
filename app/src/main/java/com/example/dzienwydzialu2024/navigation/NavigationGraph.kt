package com.example.dzienwydzialu2024.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dzienwydzialu2024.events.EventsScreen
import com.example.dzienwydzialu2024.home.HomeScreen
import com.example.dzienwydzialu2024.info.InfoScreen
import com.example.dzienwydzialu2024.tasks.TasksScreen

@Composable
fun NavigationGraph(
    navController:  NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route
    ) {
        composable(BottomNavItem.Home.route) {
            HomeScreen(modifier = Modifier.padding(paddingValues))
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