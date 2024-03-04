package com.app.dw2024

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.dw2024.model.Event
import com.app.dw2024.model.Task
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    db: FirebaseFirestore,
): ViewModel() {
    private val _mainChannel = Channel<MainEvent>()
    val mainChannel = _mainChannel.receiveAsFlow()

    var state by mutableStateOf(MainState())
        private set

    private var lectures = mutableListOf<Event>()
    private var workshops = mutableListOf<Event>()
    private var tasks = mutableListOf<Task>()

    init {
        db.collection("lectures").addSnapshotListener { value, error ->
            if (error != null) {
                return@addSnapshotListener
            }
            lectures.addAll(
                value?.map { documentSnapshot ->
                    documentSnapshot.toObject(Event::class.java)
                } ?: emptyList()
            )
            viewModelScope.launch {
                state = state.copy(lectures = lectures.distinct())
                lectures.clear()  // if lectures disappear, this might be the issue
            }
        }
        db.collection("workshops").addSnapshotListener { value, error ->
            if (error != null) {
                return@addSnapshotListener
            }
            workshops.addAll(
                value?.map { documentSnapshot ->
                    documentSnapshot.toObject(Event::class.java)
                } ?: emptyList()
            )
            viewModelScope.launch {
                state = state.copy(workshops = workshops.distinct())
                workshops.clear()  // if workshops disappear, this might be the issue
            }
        }
        db.collection("tasks").get().addOnSuccessListener { event ->
            tasks.addAll(
                event.map { documentSnapshot ->
                    documentSnapshot.toObject(Task::class.java)
                }
            )
            viewModelScope.launch {
                state = state.copy(tasks = tasks.toList())
            }
        }
    }

    fun showNoQrCodeDetected() {
        viewModelScope.launch {
            _mainChannel.send(MainEvent.OnNoQrCodeDetected)
        }
    }

    fun showSuccessfulTaskCompletion(task: Task) {
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