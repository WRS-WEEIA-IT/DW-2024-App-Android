package com.app.dw2024.repository.interfaces

import com.app.dw2024.model.Task

interface TasksRepository {
    suspend fun getTasks(): List<Task>
    suspend fun completeTask(task: Task): Boolean
    suspend fun getCompletedTasks(): List<Task>
    suspend fun getTaskByQrCode(qrCode: String): Task?
}