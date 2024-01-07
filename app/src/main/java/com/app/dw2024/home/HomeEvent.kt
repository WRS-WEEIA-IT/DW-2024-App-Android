package com.app.dw2024.home

sealed class HomeEvent {
    data object OnAllEventsClick : HomeEvent()
    data object OnAllTasksClick : HomeEvent()
    data class OnSignUpClick(val eventId: Int) : HomeEvent()
    data class OnTaskClick(val taskId: Int) : HomeEvent()
}