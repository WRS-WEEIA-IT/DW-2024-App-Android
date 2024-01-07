package com.app.dw2024.repository.interfaces

import com.app.dw2024.model.Event

interface EventsRepository {
    suspend fun getEvents(): List<Event>
}