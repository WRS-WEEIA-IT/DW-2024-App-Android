package com.app.dw2024

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.dw2024.model.Event
import com.app.dw2024.model.Task
import com.app.dw2024.repository.interfaces.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val db: FirebaseFirestore,
    private val sharedPreferences: SharedPreferences,
    private val userRepository: UserRepository
): ViewModel() {
    private val _mainChannel = Channel<MainEvent>()
    val mainChannel = _mainChannel.receiveAsFlow()

    var state by mutableStateOf(MainState())
        private set

    private var lectures = mutableSetOf<Event>()
    private var workshops = mutableSetOf<Event>()
    private var tasks = mutableSetOf<Task>()

    init {
        db.collection("lectures").addSnapshotListener { value, error ->
            if (error != null) {
                return@addSnapshotListener
            }
            lectures.addAll(
                value?.map { documentSnapshot ->
                    documentSnapshot.toObject(Event::class.java).apply { this.type = "Lecture" }
                } ?: emptyList()
            )
            viewModelScope.launch {
                state = state.copy(lectures = lectures.distinct())
            }
        }
        db.collection("workshops").addSnapshotListener { value, error ->
            if (error != null) {
                return@addSnapshotListener
            }
            workshops.addAll(
                value?.map { documentSnapshot ->
                    documentSnapshot.toObject(Event::class.java).apply { this.type = "Workshop" }
                } ?: emptyList()
            )
            viewModelScope.launch {
                state = state.copy(workshops = workshops.distinct())
            }
        }
        db.collection("tasks").addSnapshotListener { value, error ->
            if (error != null) {
                return@addSnapshotListener
            }
            tasks.addAll(
                value?.map { documentSnapshot ->
                    documentSnapshot.toObject(Task::class.java)
                } ?: emptyList()
            )
            viewModelScope.launch {
                state = state.copy(tasks = getTasksReadyForDisplay())
            }
        }
    }

    private fun getTasksReadyForDisplay(): List<Task> {
        val completedTasksNumbers = sharedPreferences.getStringSet("completed_tasks", mutableSetOf())
            ?.map { it.toInt() }
            ?.toList()
        val completedTasks = tasks.filter { task ->
            completedTasksNumbers?.contains(task.taskId) ?: false
        }
        val tasksForDisplay = tasks.map { task ->
            if (completedTasks.contains(task)) {
                task.copy(
                    isFinished = true,
                )
            } else {
                task
            }
        }
        viewModelScope.launch {
            state = state.copy(collectedPoints = completedTasks.sumOf { it.points })
        }
        return tasksForDisplay
    }

    fun forceTasksToRefresh() {
        viewModelScope.launch {
            state = state.copy(tasks = getTasksReadyForDisplay())
        }
    }

    fun completeTask(task: Task): Boolean {
        val currentlyCompletedTasks = sharedPreferences.getStringSet("completed_tasks", mutableSetOf())
        if (currentlyCompletedTasks?.contains(task.taskId.toString()) == true) {
            return false
        }
        val newCompletedTasks = currentlyCompletedTasks?.toMutableSet()
        newCompletedTasks?.add(task.taskId.toString())
        sharedPreferences.edit().putStringSet("completed_tasks", newCompletedTasks).apply()
        return true
    }

    suspend fun checkIfUserWonAfterEventAndDisplayDialogMessage() {
        val endTime = db.collection("contestTime").document("time").get().await()
            .getTimestamp("endTime")?.toDate()?.time
        if (endTime != null && System.currentTimeMillis() > endTime) {
            db.collection("users").document(userRepository.getUserId().toString()).get().addOnSuccessListener { documentSnapshot ->
                val isWinner = documentSnapshot.getBoolean("winner")
                if (isWinner == true) {
                    viewModelScope.launch {
                        state = state.copy(isWinningDialogVisible = true)
                    }
                } else {
                    viewModelScope.launch {
                        state = state.copy(isLosingDialogVisible = true)
                    }
                }
            }
        }
    }

    private fun updatePointsInFirestore() {
        val completedTasksNumbers = sharedPreferences.getStringSet("completed_tasks", mutableSetOf())
            ?.map { it.toInt() }
            ?.toList()
        Log.d("MainViewModel", "completedTasksNumbers: $completedTasksNumbers")
        Log.d("MainViewModel", "tasks: $tasks")
        val completedTasks = tasks.filter { task ->
            completedTasksNumbers?.contains(task.taskId) ?: false
        }
        Log.d("MainViewModel", "completedTasks: $completedTasks")
        viewModelScope.launch {
            val sum = completedTasks.sumOf { it.points }
            Log.d("MainViewModel", "sum: $sum")
            userRepository.updateUserPointsInFirestore(completedTasks.sumOf { it.points })
        }
    }

    fun onDialogDismiss() {
        viewModelScope.launch {
            state = state.copy(isWinningDialogVisible = false, isLosingDialogVisible = false)
        }
    }

    fun showNoQrCodeDetected() {
        viewModelScope.launch {
            _mainChannel.send(MainEvent.OnNoQrCodeDetected)
        }
    }

    fun onSuccessfulTaskCompletion(task: Task) {
        updatePointsInFirestore()
        viewModelScope.launch {
            _mainChannel.send(MainEvent.OnSuccessfulTaskCompletion(task))
        }
    }

    fun showTaskAlreadyFinished() {
        viewModelScope.launch {
            _mainChannel.send(MainEvent.OnTaskAlreadyFinished)
        }
    }

    fun showNoSuchTaskExists() {
        viewModelScope.launch {
            _mainChannel.send(MainEvent.OnNoSuchTaskExists)
        }
    }
}