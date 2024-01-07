package com.app.dw2024.repository.impl

import com.app.dw2024.model.Task
import com.app.dw2024.repository.interfaces.TasksRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class TasksRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
): TasksRepository {
    override suspend fun getTasks(): List<Task> {
        val tasks = mutableListOf<Task>()
        val tasksCollection = db.collection("tasks").get().await()
        tasks.addAll(
            tasksCollection.map { documentSnapshot ->
                documentSnapshot.toObject(Task::class.java)
            }
        )
        return tasks
    }
}