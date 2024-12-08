@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.composenavigationsample.ui.settingScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composenavigationsample.ui.AppScreens

@Composable
fun SettingScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(AppScreens.Setting.title) }
            )
        },
        modifier = modifier,
    ) { safeInsets ->
        Column(
            modifier = Modifier
                .padding(safeInsets)
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.weight(1F)
            ) {
                Text("設定")
            }
            Button(
                onClick = { navController.popBackStack() }
            ) {
                Text("完了")
            }
        }
    }
}