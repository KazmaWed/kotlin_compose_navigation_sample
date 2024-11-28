package com.example.composenavigationsample.ui

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composenavigationsample.ui.loginScreen.LoginScreen
import com.example.composenavigationsample.ui.mainScreen.MainScreen
import com.example.composenavigationsample.ui.mainScreen.MainScreenViewModel
import com.example.composenavigationsample.ui.settingScreen.SettingScreen


/**
 * 画面クラスインターフェース
 *
 * @param title 画面に表示するタイトル文字列
 * @param route 画面のパスを表す固有のString
 */
interface AppDestination {
    val title: String
    val route: String
}

sealed class AppScreen(
    override val title: String,
    override val route: String
) : AppDestination {
    object Login : AppScreen("ログイン", "login")
    object Main : AppScreen("メイン", "main")
    object Setting : AppScreen("設定", "setting")
}

@Composable
fun AppNavHost(
    mainViewModel: MainScreenViewModel = MainScreenViewModel(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = AppScreen.Login.route,
    ) {
        composable(
            route = AppScreen.Login.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
        ) { backStackEntry ->
            LoginScreen(navController)
        }
        composable(
            route = AppScreen.Main.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
        ) { backStackEntry ->
            mainViewModel.navigateContent(IncrementScreens.Index)
            MainScreen(mainViewModel, navController)
        }
        composable(
            route = AppScreen.Setting.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { -it }) },
            popEnterTransition = { slideInVertically(initialOffsetY = { -it }) },
            popExitTransition = { slideOutVertically(targetOffsetY = { it }) },
        ) { backStackEntry ->
            SettingScreen(navController)
        }
    }
}