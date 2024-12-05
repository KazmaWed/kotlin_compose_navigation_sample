package com.example.composenavigationsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composenavigationsample.ui.AppNavHost
import com.example.composenavigationsample.ui.SharedViewModel
import com.example.composenavigationsample.ui.ViewModelProvider
import com.example.composenavigationsample.ui.theme.ComposeNavigationSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val localSharedViewModel = compositionLocalOf<SharedViewModel> { error("SharedViewModel not provided") }
            CompositionLocalProvider(
                localSharedViewModel provides viewModel(factory = ViewModelProvider.Factory)
            ) {
                ComposeNavigationSampleTheme {
                    AppNavHost()
                }
            }
        }
    }
}