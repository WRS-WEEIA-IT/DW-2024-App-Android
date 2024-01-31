package com.app.dw2024.events

import androidx.compose.ui.geometry.Offset
import com.app.dw2024.model.Event
import com.app.dw2024.model.Filter

sealed class EventsEvent {
    data object OnNewEventsDetected : EventsEvent()
    data class OnChangeFilter(val filter: Filter) : EventsEvent()
    data class OnSignUpClick(val event: Event) : EventsEvent()
    data object OnBottomModalSheetShow : EventsEvent()
    data object OnBottomModalSheetDismiss : EventsEvent()
}