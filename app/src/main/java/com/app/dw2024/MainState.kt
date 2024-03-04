package com.app.dw2024

import com.app.dw2024.model.Event
import com.app.dw2024.model.Task

data class MainState(
    val tasks: List<Task> = listOf(),
    val lectures: List<Event> = listOf(),
    val workshops: List<Event> = listOf(),
)