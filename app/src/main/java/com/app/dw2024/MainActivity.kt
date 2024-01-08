package com.app.dw2024

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.app.dw2024.repository.interfaces.EventsRepository
import com.app.dw2024.repository.interfaces.UserRepository
import com.app.dw2024.ui.theme.DarkBlack
import com.app.dw2024.ui.theme.DzienWydzialu2024Theme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DzienWydzialu2024Theme {
                if (userRepository.getUserId() == 0) {
                    LaunchedEffect(key1 = true) {
                        userRepository.createNewUser()
                    }
                }
                val navController = rememberNavController()
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