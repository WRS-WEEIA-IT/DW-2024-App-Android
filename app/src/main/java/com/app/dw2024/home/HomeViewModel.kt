package com.app.dw2024.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.app.dw2024.R
import com.app.dw2024.model.Event
import com.app.dw2024.model.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

): ViewModel() {
    var state by mutableStateOf(HomeState())
        private set

    init {
        state = state.copy(tasks = listOf(
            Task(1, "Zdjęcie zabytku", "Zrób zdjęcie znanego zabytku.", 10, false, R.drawable.card_background_image),
            Task(2, "Kod szyfru", "Znajdź i rozszyfruj ukryty kod.", 15, false, R.drawable.card_background_image),
            Task(3, "Quiz historyczny", "Odpowiedz na pytanie o historię miasta.", 5, false, R.drawable.card_background_image),
            Task(4, "Skarb miejski", "Znajdź ukryty skarb w parku.", 20, false, R.drawable.card_background_image),
            Task(5, "Ptaki parkowe", "Zidentyfikuj rodzaje ptaków w parku.", 10, false, R.drawable.card_background_image),
            Task(6, "Pomnik selfie", "Zrób selfie z pomnikiem.", 5, false, R.drawable.card_background_image),
            Task(7, "Śladami historii", "Śledź trasę historyczną miasta.", 15, false, R.drawable.card_background_image),
            Task(8, "Zagadka architektoniczna", "Rozwiąż zagadkę dotyczącą budynku.", 10, false, R.drawable.card_background_image)
        ))
        state = state.copy(events = listOf(
            Event(1, "Koncert", "Rockowa Noc", "Stadion Narodowy", LocalDateTime.of(2024, 5, 1, 20, 0), LocalDateTime.of(2024, 5, 1, 23, 59), "rock_night.jpg"),
            Event(2, "Konferencja", "Nowe Technologie", "Centrum Kongresowe", LocalDateTime.of(2024, 6, 15, 9, 0), LocalDateTime.of(2024, 6, 17, 18, 0), "tech_conference.jpg"),
            Event(3, "Wystawa", "Sztuka Nowoczesna", "Muzeum Sztuki", LocalDateTime.of(2024, 7, 20, 10, 0), LocalDateTime.of(2024, 8, 30, 17, 0), "modern_art.jpg"),
            Event(4, "Festiwal Filmowy", "Noc Kina", "Kino Helios", LocalDateTime.of(2024, 9, 10, 19, 0), LocalDateTime.of(2024, 9, 15, 23, 0), "film_festival.jpg"),
            Event(5, "Maraton", "Bieg Przez Miasto", "Park Miejski", LocalDateTime.of(2024, 10, 5, 7, 0), LocalDateTime.of(2024, 10, 5, 14, 0), "city_marathon.jpg"),
            Event(6, "Targi Książki", "Świat Literatury", "Hala Targowa", LocalDateTime.of(2024, 11, 25, 9, 0), LocalDateTime.of(2024, 11, 30, 20, 0), "book_fair.jpg"),
            Event(7, "Spektakl", "Teatr Cieni", "Teatr Wielki", LocalDateTime.of(2024, 12, 1, 18, 30), LocalDateTime.of(2024, 12, 3, 21, 0), "shadow_play.jpg"),
            Event(8, "Turniej Szachowy", "Mistrzowie Szachów", "Centrum Sportowe", LocalDateTime.of(2025, 1, 15, 10, 0), LocalDateTime.of(2025, 1, 20, 17, 0), "chess_tournament.jpg")
        ))
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnAllEventsClick -> {

            }

            is HomeEvent.OnAllTasksClick -> {

            }

            is HomeEvent.OnSignUpClick -> {

            }

            is HomeEvent.OnTaskClick -> {

            }
        }
    }
}