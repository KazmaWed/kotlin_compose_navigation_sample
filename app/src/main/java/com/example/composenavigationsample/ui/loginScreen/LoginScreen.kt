@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.composenavigationsample.ui.loginScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.composenavigationsample.ui.AppScreens

@Composable
fun LoginScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ログイン") }
            )
        },
        modifier = modifier
    ) { safeInsets ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(safeInsets)
                .fillMaxSize()
        ) {
            Button(
                onClick = { navController.navigate(AppScreens.Main.route) }
            ) {
                Text("ログインする")
            }
        }
    }
}