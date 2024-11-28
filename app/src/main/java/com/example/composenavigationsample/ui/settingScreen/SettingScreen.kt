package com.example.composenavigationsample.ui.settingScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun SettingScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
    ) { safeInsets ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(safeInsets)
        ) {

        }
    }
}