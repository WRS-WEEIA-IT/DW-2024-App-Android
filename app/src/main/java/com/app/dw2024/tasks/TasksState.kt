package com.app.dw2024.tasks

import com.app.dw2024.model.Task

data class TasksState(
    val tasks: List<Task> = listOf(),
    val collectedPoints: Int = 0,
)
