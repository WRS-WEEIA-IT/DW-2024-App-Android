package com.app.dw2024.tasks

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.dw2024.R
import com.app.dw2024.model.Task
import com.app.dw2024.model.getTasksForDisplay
import com.app.dw2024.repository.interfaces.TasksRepository
import com.app.dw2024.repository.interfaces.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val tasksRepository: TasksRepository,
    private val userRepository: UserRepository
): ViewModel() {
    var state by mutableStateOf(TasksState())
        private set

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            val tasks = tasksRepository.getTasks()
            val completedTasks = tasksRepository.getCompletedTasks()
            state = state.copy(tasks = getTasksForDisplay(tasks, completedTasks))
            state = state.copy(
                collectedPoints = userRepository.getUserInfo()?.points ?: -1
            )
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