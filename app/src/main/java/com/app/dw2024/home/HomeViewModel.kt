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