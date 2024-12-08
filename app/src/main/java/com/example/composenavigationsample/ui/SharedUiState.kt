package com.example.composenavigationsample.ui

data class SharedUiState(
    val currentScreen: AppDestination = AppScreens.Login,
)
