package com.example.composenavigationsample.ui.mainScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.composenavigationsample.ui.AppScreen
import com.example.composenavigationsample.ui.IncrementScreens
import com.example.composenavigationsample.ui.IncrementScreenNavHost
import com.example.composenavigationsample.ui.NavigatorScreens
import com.example.composenavigationsample.ui.NavigatorScreenNavHost
import com.example.composenavigationsample.ui.incrementScreen.IncrementScreenViewModel

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel,
    globalNavController: NavController,
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.collectAsState()

    val incrementScreenNavController = rememberNavController()
    val incrementScreenViewModel = IncrementScreenViewModel()
    val navigatorScreenNavController = rememberNavController()

    fun currentScreenNavController(): NavController {
        if (uiState.value.selectedItem is IncrementScreens) {
            return incrementScreenNavController
        } else {
            return navigatorScreenNavController
        }
    }

    Scaffold(
        topBar = {
            MainScreenTopAppBar(
                title = uiState.value.currentScreen.title,
                globalNavController = globalNavController,
                navController = currentScreenNavController(),
                onClickSetting = {
                    globalNavController.navigate(AppScreen.Setting.route)
                }
            )
        },
        bottomBar = {
            MainScreenBottomAppBar(
                selectedItem = uiState.value.selectedItem,
                onClick = { destination -> viewModel.selectBottomBarItem(destination) }
            )
        },
        modifier = modifier
    ) { safeInsets ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(safeInsets)
        ) {
            when (uiState.value.selectedItem) {
                is IncrementScreens -> IncrementScreenNavHost(
                    incrementScreenViewModel,
                    incrementScreenNavController
                )

                is NavigatorScreens -> NavigatorScreenNavHost(
                    navigatorScreenNavController,
                    viewModel
                )
            }
        }
    }
}