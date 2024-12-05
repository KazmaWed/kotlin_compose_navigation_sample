package com.example.composenavigationsample.ui.mainScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.composenavigationsample.ui.AppScreen
import com.example.composenavigationsample.ui.IncrementScreens
import com.example.composenavigationsample.ui.IncrementScreenNavHost
import com.example.composenavigationsample.ui.NavigatorScreens
import com.example.composenavigationsample.ui.NavigatorScreenNavHost
import com.example.composenavigationsample.ui.SharedViewModel

@Composable
fun MainScreen(
    globalNavController: NavController,
    modifier: Modifier = Modifier,
    viewModel: MainScreenViewModel = viewModel(),
    sharedViewModel: SharedViewModel = viewModel(),
) {
    val uiState = viewModel.uiState.collectAsState()
    val sharedUiState = sharedViewModel.uiState.collectAsState()

    val incrementScreenNavController = rememberNavController()
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
                title = sharedUiState.value.currentScreen.title,
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
                is IncrementScreens -> IncrementScreenNavHost(incrementScreenNavController)
                is NavigatorScreens -> NavigatorScreenNavHost(navigatorScreenNavController)
            }
        }
    }
}