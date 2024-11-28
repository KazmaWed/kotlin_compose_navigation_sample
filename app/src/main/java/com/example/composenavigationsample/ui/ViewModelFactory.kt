package com.example.composenavigationsample.ui

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.composenavigationsample.ui.mainScreen.MainScreenViewModel

object ViewModelProvider {
    val Factory = viewModelFactory {
        initializer { MainScreenViewModel() }
    }
}