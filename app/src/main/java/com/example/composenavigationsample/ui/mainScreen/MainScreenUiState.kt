package com.example.composenavigationsample.ui.mainScreen

import com.example.composenavigationsample.ui.AppDestination
import com.example.composenavigationsample.ui.IncrementScreens

data class MainScreenUiState(
    val selectedItem: AppDestination = IncrementScreens.Index
)