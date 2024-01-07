package com.example.dzienwydzialu2024.events

import com.example.dzienwydzialu2024.model.Event
import com.example.dzienwydzialu2024.model.Filter

sealed class EventsEvent {
    data object OnNewEventsDetected : EventsEvent()
    data class OnChangeFilter(val filter: Filter) : EventsEvent()
    data class OnSignUpClick(val event: Event) : EventsEvent()
}