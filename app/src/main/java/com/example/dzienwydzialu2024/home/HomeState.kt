package com.example.dzienwydzialu2024.home

import com.example.dzienwydzialu2024.model.Event
import com.example.dzienwydzialu2024.model.Task

data class HomeState(
    val events : List<Event> = listOf(),
    val tasks : List<Task> = listOf(),
)
