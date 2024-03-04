package com.app.dw2024.info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.dw2024.repository.interfaces.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    var state by mutableStateOf(InfoState())
        private set

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            state = state.copy(
                userId = userRepository.getUserId().toString(),
            )
        }
    }
}