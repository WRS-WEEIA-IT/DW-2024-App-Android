package com.app.dw2024.info

sealed class InfoEvent {
    data object OnFlagClick : InfoEvent()
    data class OnPointsClick(val points: Int) : InfoEvent()
}