package com.app.dw2024

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.dw2024.model.Task
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _mainChannel = Channel<MainEvent>()
    val mainChannel = _mainChannel.receiveAsFlow()

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