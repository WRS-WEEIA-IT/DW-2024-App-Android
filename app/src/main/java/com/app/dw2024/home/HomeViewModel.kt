package com.app.dw2024.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.dw2024.R
import com.app.dw2024.model.Event
import com.app.dw2024.model.Task
import com.app.dw2024.repository.interfaces.EventsRepository
import com.app.dw2024.repository.interfaces.TasksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val eventsRepository: EventsRepository,
    private val tasksRepository: TasksRepository
): ViewModel() {
    var state by mutableStateOf(HomeState())
        private set

    init {
        viewModelScope.launch {
            val tasks = tasksRepository.getTasks()
            val events = eventsRepository.getEvents()
            state = state.copy(tasks = tasks)
            state = state.copy(events = events)
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnAllEventsClick -> {

            }

            is HomeEvent.OnAllTasksClick -> {

            }

            is HomeEvent.OnSignUpClick -> {

            }

            is HomeEvent.OnTaskClick -> {

            }
        }
    }
}