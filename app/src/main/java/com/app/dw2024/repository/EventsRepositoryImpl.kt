package com.app.dw2024.repository

import com.app.dw2024.model.Event
import com.app.dw2024.repository.interfaces.EventsRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class EventsRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
): EventsRepository {
    override suspend fun getEvents(): List<Event> {
        val events = mutableListOf<Event>()
        val eventsCollection = db.collection("lectures").get().await()
        for (event in eventsCollection) {
            events.add(event.toObject(Event::class.java))
        }
        return events
    }
}