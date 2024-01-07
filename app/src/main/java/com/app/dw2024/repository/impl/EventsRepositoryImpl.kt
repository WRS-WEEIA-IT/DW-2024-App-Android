package com.app.dw2024.repository.impl

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
        val eventsLecturesCollection = db.collection("lectures").get().await()
        val eventsWorkshopsCollection = db.collection("workshops").get().await()
        events.addAll(
            (eventsLecturesCollection + eventsWorkshopsCollection).map { documentSnapshot ->
                documentSnapshot.toObject(Event::class.java).apply {
                    type =
                        if (documentSnapshot.reference.path.startsWith("lectures")) "Lecture" else "Workshop"
                }
            }
        )
        return events
    }
}