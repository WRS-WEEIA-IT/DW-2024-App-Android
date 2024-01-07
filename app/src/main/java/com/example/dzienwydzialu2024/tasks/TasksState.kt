package com.example.dzienwydzialu2024.tasks

import com.example.dzienwydzialu2024.model.Task

data class TasksState(
    val tasks: List<Task> = listOf(),
    val collectedPoints: Int = 0,
)
