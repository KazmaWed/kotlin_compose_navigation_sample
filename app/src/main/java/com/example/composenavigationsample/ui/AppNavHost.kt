package com.example.composenavigationsample.ui

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composenavigationsample.ui.loginScreen.LoginScreen
import com.example.composenavigationsample.ui.mainScreen.MainScreen
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

sealed class AppScreens(
    override val title: String,
    override val route: String
) : AppDestination {
    object Login : AppScreens("ログイン", "login")
    object Main : AppScreens("メイン", "main")
    object Setting : AppScreens("設定", "setting")
}

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    sharedViewModel: SharedViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = AppScreens.Login.route,
    ) {
        composable(
            route = AppScreens.Login.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
        ) { backStackEntry ->
            sharedViewModel.navigate(AppScreens.Login)
            LoginScreen(navController)
        }
        composable(
            route = AppScreens.Main.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
        ) { backStackEntry ->
            sharedViewModel.navigate(AppScreens.Main)
            MainScreen(navController)
        }
        composable(
            route = AppScreens.Setting.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
        ) { backStackEntry ->
            sharedViewModel.navigate(AppScreens.Setting)
            SettingScreen(navController)
        }
    }
}