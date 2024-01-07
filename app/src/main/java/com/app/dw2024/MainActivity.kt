package com.app.dw2024

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.app.dw2024.repository.interfaces.EventsRepository
import com.app.dw2024.ui.theme.DarkBlack
import com.app.dw2024.ui.theme.DzienWydzialu2024Theme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var eventsRepository: EventsRepository

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