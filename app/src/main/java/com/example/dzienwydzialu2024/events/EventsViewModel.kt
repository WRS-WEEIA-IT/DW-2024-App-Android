package com.example.dzienwydzialu2024.events

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dzienwydzialu2024.model.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(

): ViewModel() {
    var state by mutableStateOf(EventsState())
        private set

    init {
        viewModelScope.launch {
            // TODO: Load events from repo
            state = state.copy(events = listOf(
                Event(1, "Koncert", "Rockowa Noc", "Stadion Narodowy", LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 1), "card_background_image.png"),
                Event(2, "Konferencja", "Nowe Technologie", "Centrum Kongresowe", LocalDate.of(2024, 6, 15), LocalDate.of(2024, 6, 17), "card_background_image.png"),
                Event(3, "Wystawa", "Sztuka Nowoczesna", "Muzeum Sztuki", LocalDate.of(2024, 7, 20), LocalDate.of(2024, 8, 30), "card_background_image.png"),
                Event(4, "Festiwal Filmowy", "Noc Kina", "Kino Helios", LocalDate.of(2024, 9, 10), LocalDate.of(2024, 9, 15), "card_background_image.png"),
                Event(5, "Maraton", "Bieg Przez Miasto", "Park Miejski", LocalDate.of(2024, 10, 5), LocalDate.of(2024, 10, 5), "card_background_image.png"),
                Event(6, "Targi Książki", "Świat Literatury", "Hala Targowa", LocalDate.of(2024, 11, 25), LocalDate.of(2024, 11, 30), "card_background_image.png"),
                Event(7, "Spektakl", "Teatr Cieni", "Teatr Wielki", LocalDate.of(2024, 12, 1), LocalDate.of(2024, 12, 3), "card_background_image.png"),
                Event(8, "Turniej Szachowy", "Mistrzowie Szachów", "Centrum Sportowe", LocalDate.of(2025, 1, 15), LocalDate.of(2025, 1, 20), "card_background_image.png")
            ))
        }
    }

    fun onEvent(event: EventsEvent) {
        when (event) {
            is EventsEvent.OnNewEventsDetected -> {

            }

            is EventsEvent.OnChangeFilter -> {

            }

            is EventsEvent.OnSignUpClick -> {

            }
        }
    }
}