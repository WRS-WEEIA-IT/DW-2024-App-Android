package com.app.dw2024

import com.app.dw2024.model.Task

sealed class MainEvent {
    data object OnNoQrCodeDetected: MainEvent()
    data class OnSuccessfulTaskCompletion(val task: Task): MainEvent()
    data object OnTaskAlreadyFinished: MainEvent()
    data object OnNoSuchTaskExists: MainEvent()
}