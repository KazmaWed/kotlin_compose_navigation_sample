package com.example.composenavigationsample.ui.incrementScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class IncrementScreenUiState(
    val count: Int = 0
)

class IncrementScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(IncrementScreenUiState())
    val uiState: StateFlow<IncrementScreenUiState> = _uiState

    fun increment() = _uiState.update {
        it.copy(it.count + 1)
    }
}