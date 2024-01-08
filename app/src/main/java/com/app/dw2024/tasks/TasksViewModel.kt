package com.app.dw2024.tasks

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.dw2024.R
import com.app.dw2024.model.Task
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
            val tasksForDisplay = getTasksForDisplay(tasks)
            state = state.copy(tasks = tasksForDisplay)
            state = state.copy(
                collectedPoints = userRepository.getUserInfo()?.points ?: -1
            )
        }
    }

    private suspend fun getTasksForDisplay(tasks: List<Task>): List<Task> {
        val completedTasks = tasksRepository.getCompletedTasks()
        val tasksForDisplay = tasks.map { task ->
            if (completedTasks.contains(task)) {
                task.copy(
                    isFinished = true,
                )
            } else {
                task
            }
        }
        return tasksForDisplay
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