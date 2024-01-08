package com.app.dw2024.repository.impl

import android.content.SharedPreferences
import com.app.dw2024.model.Task
import com.app.dw2024.repository.interfaces.TasksRepository
import com.app.dw2024.repository.interfaces.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class TasksRepositoryImpl @Inject constructor(
    private val userRepository: UserRepository,
    private val db: FirebaseFirestore,
    private val sharedPreferences: SharedPreferences
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

    override suspend fun completeTask(task: Task): Boolean {
        val currentlyCompletedTasks = sharedPreferences.getStringSet("completed_tasks", mutableSetOf())
        if (currentlyCompletedTasks?.contains(task.taskNumber.toString()) == true) {
            return false
        }
        val newCompletedTasks = currentlyCompletedTasks?.toMutableSet()
        newCompletedTasks?.add(task.taskNumber.toString())
        sharedPreferences.edit().putStringSet("completed_tasks", newCompletedTasks).apply()
        userRepository.incrementUserPoints(task.points)
        return true
    }

    override suspend fun getCompletedTasks(): List<Task> {
        val completedTasks = mutableListOf<Task>()
        val completedTasksNumbers = sharedPreferences.getStringSet("completed_tasks", mutableSetOf())
        val tasks = getTasks()
        completedTasks.addAll(
            tasks.filter { task ->
                completedTasksNumbers?.contains(task.taskNumber.toString()) ?: false
            }
        )
        return completedTasks
    }

    override suspend fun getTaskByQrCode(qrCode: String): Task? {
        val tasks = getTasks()
        return tasks.find { task ->
            task.qrCode == qrCode
        }
    }
}