package com.app.dw2024.tasks

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.dw2024.R
import com.app.dw2024.model.Task
import com.app.dw2024.repository.interfaces.TasksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val tasksRepository: TasksRepository
): ViewModel() {
    var state by mutableStateOf(TasksState())
        private set

    init {
        viewModelScope.launch {
            val tasks = tasksRepository.getTasks()
            state = state.copy(tasks = tasks)
        }
    }

    fun onEvent(event: TasksEvent) {
        when (event) {
            is TasksEvent.OnTaskClick -> {

            }

            is TasksEvent.OnTaskFinish -> {

            }

            is TasksEvent.OnNewTasksDetected -> {

            }
        }
    }
}