package com.app.dw2024.home

import com.app.dw2024.model.Event
import com.app.dw2024.model.Task

data class HomeState(
    val events : List<Event> = listOf(),
    val tasks : List<Task> = listOf(),
)
