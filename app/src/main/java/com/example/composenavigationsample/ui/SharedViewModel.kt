package com.example.composenavigationsample.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

class SharedViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SharedUiState())
    val uiState: StateFlow<SharedUiState> = _uiState

    fun navigate(screen: AppDestination) = _uiState.update {
        it.copy(currentScreen = screen)
    }
}