package com.example.composenavigationsample.ui.mainScreen

import androidx.lifecycle.ViewModel
import com.example.composenavigationsample.ui.AppDestination
import com.example.composenavigationsample.ui.AppScreen
import com.example.composenavigationsample.ui.IncrementScreens
import kotlinx.coroutines.flow.*

data class MainScreenUiState(
    val currentScreen: AppDestination = AppScreen.Login,
    val selectedItem: AppDestination = IncrementScreens.Index
)

class MainScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MainScreenUiState())
    val uiState: StateFlow<MainScreenUiState> = _uiState

    fun selectBottomBarItem(screen: AppDestination) = _uiState.update {
        it.copy(
            currentScreen = screen,
            selectedItem = screen
        )
    }

    fun navigateContent(screen: AppDestination) = _uiState.update {
        it.copy(currentScreen = screen)
    }
}