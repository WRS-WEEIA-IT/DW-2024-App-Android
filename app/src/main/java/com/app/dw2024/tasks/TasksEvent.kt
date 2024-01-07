package com.app.dw2024.tasks

import com.app.dw2024.model.Task

sealed class TasksEvent {
    data class OnTaskClick(val taskId: Int) : TasksEvent()
    data class OnTaskFinish(val taskId: Int) : TasksEvent()
    data class OnNewTasksDetected(val tasks: List<Task>) : TasksEvent()
}