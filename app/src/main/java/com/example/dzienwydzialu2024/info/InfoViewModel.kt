package com.example.dzienwydzialu2024.info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(

): ViewModel() {
    var state by mutableStateOf(InfoState())
        private set

    init {
        state = state.copy(userId = "1234")
    }

    fun onEvent(event: InfoEvent) {
        when (event) {
            is InfoEvent.OnFlagClick -> {

            }

            is InfoEvent.OnPointsClick -> {

            }
        }
    }
}