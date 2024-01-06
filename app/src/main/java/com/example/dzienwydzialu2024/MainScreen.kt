package com.example.dzienwydzialu2024

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dzienwydzialu2024.navigation.BottomNavigationBar
import com.example.dzienwydzialu2024.navigation.NavigationGraph
import com.example.dzienwydzialu2024.ui.theme.DarkBlack

@Composable
fun MainScreen(
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkBlack)
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = "Logo",
                    modifier = Modifier.height(76.dp)
                )
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
//        floatingActionButtonPosition = FabPosition.Center,
//        isFloatingActionButtonDocked = true,
//        floatingActionButton = {
//            FloatingActionButton(
//                shape = CircleShape,
//                onClick = { /*TODO*/ },
//                backgroundColor = Color.Magenta,
//                contentColor = Color.White,
//            ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.ic_qr_code),
//                    contentDescription = "QR Code Scanner"
//                )
//            }
//        }
    ) {
        NavigationGraph(
            navController = navController,
            paddingValues = it
        )
    }
}