package com.example.composenavigationsample.ui.mainScreen

import androidx.lifecycle.ViewModel
import com.example.composenavigationsample.ui.AppDestination
import kotlinx.coroutines.flow.*

class MainScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MainScreenUiState())
    val uiState: StateFlow<MainScreenUiState> = _uiState

    fun selectBottomBarItem(screen: AppDestination) = _uiState.update {
        it.copy(
            selectedItem = screen
        )
    }
}