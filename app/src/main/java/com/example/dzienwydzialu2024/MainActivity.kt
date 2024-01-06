package com.example.dzienwydzialu2024

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.dzienwydzialu2024.ui.theme.DarkBlack
import com.example.dzienwydzialu2024.ui.theme.DzienWydzialu2024Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DzienWydzialu2024Theme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = DarkBlack
                ) {
                    MainScreen(navController)
                }
            }
        }
    }
}