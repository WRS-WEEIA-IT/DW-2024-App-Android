package com.app.dw2024.events

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.dw2024.repository.interfaces.EventsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val eventsRepository: EventsRepository
): ViewModel() {
    var state by mutableStateOf(EventsState())
        private set

    init {
        viewModelScope.launch {
            val events = eventsRepository.getEvents()
            state = state.copy(events = events)
        }
    }

    fun onEvent(event: EventsEvent) {
        when (event) {
            is EventsEvent.OnNewEventsDetected -> {

            }

            is EventsEvent.OnChangeFilter -> {

            }

            is EventsEvent.OnSignUpClick -> {

            }
        }
    }
}