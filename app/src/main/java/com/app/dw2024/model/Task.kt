package com.app.dw2024.model

data class Task(
    val taskId: Int = 0,
    val title: String = "",
    val description: String = "",
    val points: Int = 0,
    val isFinished: Boolean = false,
    val imageSrc: String = "",
    val qrCode: String = "",
)

fun getTasksForDisplay(tasks: List<Task>, completedTasks: List<Task>): List<Task> {
    val tasksForDisplay = tasks.map { task ->
        if (completedTasks.contains(task)) {
            task.copy(
                isFinished = true,
            )
        } else {
            task
        }
    }
    return tasksForDisplay
}