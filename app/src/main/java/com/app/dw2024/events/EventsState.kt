package com.app.dw2024.events

import com.app.dw2024.model.Event
import com.app.dw2024.model.Filter

data class EventsState(
    val events: List<Event> = listOf(),
    val filter: Filter = Filter.BY_DATE,
    val showBottomSheet: Boolean = false,
)
