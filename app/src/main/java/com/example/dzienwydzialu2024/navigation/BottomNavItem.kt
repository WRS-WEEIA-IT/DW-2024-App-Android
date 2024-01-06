package com.example.dzienwydzialu2024.navigation

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.dzienwydzialu2024.R

sealed class BottomNavItem(val route: String, @DrawableRes val icon: Int, val title: String) {
    data object Home : BottomNavItem("home", R.drawable.ic_compass, "Home")
    data object Events : BottomNavItem("events", R.drawable.ic_study, "Events")
    data object QrCodeScanner : BottomNavItem("qr_code_scanner", R.drawable.ic_qr_code, "QrCodeScanner")
    data object Tasks : BottomNavItem("tasks", R.drawable.ic_trophy, "Tasks")
    data object Info : BottomNavItem("info", R.drawable.ic_info, "Info")
}