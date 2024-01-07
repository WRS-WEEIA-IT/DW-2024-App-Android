package com.example.dzienwydzialu2024.events

import com.example.dzienwydzialu2024.model.Event
import com.example.dzienwydzialu2024.model.Filter

data class EventsState(
    val events: List<Event> = listOf(),
    val filter: Filter = Filter.BY_DATE,
)
