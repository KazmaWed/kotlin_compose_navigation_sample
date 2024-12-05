@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.composenavigationsample.ui.mainScreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.composenavigationsample.ui.AppDestination
import com.example.composenavigationsample.ui.IncrementScreens
import com.example.composenavigationsample.ui.NavigatorScreens

@Composable
fun MainScreenTopAppBar(
    title: String,
    navController: NavController,
    onClickSetting: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(title)
        },
        navigationIcon = {
            if (navController.previousBackStackEntry != null) {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(Icons.Outlined.KeyboardArrowLeft, "戻るボタン")
                }
            }
        },
        actions = {
            IconButton(
                onClick = { onClickSetting() }
            ) {
                Icon(Icons.Outlined.Settings, "設定ボタン")
            }
        }
    )
}

@Composable
fun MainScreenBottomAppBar(
    selectedItem: AppDestination,
    onClick: (AppDestination) -> Unit
) {
    BottomAppBar {
        NavigationBarItem(
            selected = selectedItem is IncrementScreens,
            label = { Text("インクリメント") },
            icon = { Icon(Icons.Default.AddCircle, "インクリメント画面タブ") },
            onClick = { onClick(IncrementScreens.Index) }
        )
        NavigationBarItem(
            selected = selectedItem is NavigatorScreens,
            label = { Text("ナビゲーション") },
            icon = { Icon(Icons.Default.PlayArrow, "ナビゲーション画面タブ") },
            onClick = { onClick(NavigatorScreens.Index) }
        )
    }
}