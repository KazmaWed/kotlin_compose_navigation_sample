package com.example.composenavigationsample.ui.incrementScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun IncrementScreen(
    modifier: Modifier = Modifier,
    viewModel: IncrementScreenViewModel = viewModel(),
) {
    val uiState = viewModel.uiState.collectAsState()

    Scaffold(
        modifier = modifier
    ) { safeInsets ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(safeInsets)
                .fillMaxSize()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1F)
            ) {
                Text(
                    uiState.value.count.toString() + " クリック",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            FloatingActionButton(
                onClick = { viewModel.increment() },
            ) {
                Icon(Icons.Default.Add, "インクリメントボタン")
            }
        }
    }
}