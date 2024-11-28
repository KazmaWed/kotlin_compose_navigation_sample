package com.example.composenavigationsample.ui

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composenavigationsample.ui.incrementScreen.IncrementScreen
import com.example.composenavigationsample.ui.incrementScreen.IncrementScreenViewModel
import com.example.composenavigationsample.ui.mainScreen.MainScreenViewModel
import com.example.composenavigationsample.ui.navigatorScreen.NavigatorScreen

sealed class IncrementScreens(
    override val title: String,
    override val route: String
) : AppDestination {
    object Index : IncrementScreens("インクリメント", "increment/index")
}

sealed class NavigatorScreens(
    override val title: String,
    override val route: String
) : AppDestination {
    data class Page(val count: Int) : NavigatorScreens(
        "ページ ${count} ",
        navigatorScreen(count)
    )
}

private val navigatorScreenRoute
    get() = "navigator"

private fun navigatorScreen(count: Int): String {
    return navigatorScreenRoute + "?count=${count.toString()}"
}

private val navigatorScreenComposableRoute
    get() = "$navigatorScreenRoute?count={count}"


@Composable
fun IncrementScreenNavHost(
    incrementScreenViewModel: IncrementScreenViewModel,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = IncrementScreens.Index.route,
    ) {
        composable(
            route = IncrementScreens.Index.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
        ) { backStackEntry ->
            IncrementScreen(incrementScreenViewModel)
        }
    }
}

@Composable
fun NavigatorScreenNavHost(
    navController: NavHostController,
    mainScreenViewModel: MainScreenViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = NavigatorScreens.Page(1).route,
    ) {
        composable(
            route = navigatorScreenComposableRoute,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
        ) { backStackEntry ->
            val count = backStackEntry.arguments?.getString("count")!!.toInt()
            mainScreenViewModel.navigateContent(
                NavigatorScreens.Page(count)
            )
            NavigatorScreen(count, navController)
        }
    }
}