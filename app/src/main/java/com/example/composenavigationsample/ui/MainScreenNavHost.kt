package com.example.composenavigationsample.ui

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composenavigationsample.ui.incrementScreen.IncrementScreen
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
    object Index : NavigatorScreens("ページ 1", "$navigatorScreensRoute/index")
    data class Page(val count: Int) : NavigatorScreens(
        "ページ $count ",
        navigatorScreensRouteWithCount(count)
    )
}

private val navigatorScreensRoute
    get() = "navigator"

private fun navigatorScreensRouteWithCount(count: Int): String {
    return navigatorScreensRoute + "?count=${count.toString()}"
}

private val navigatorScreensRouteFormat
    get() = "$navigatorScreensRoute?count={count}"

@Composable
fun IncrementScreenNavHost(
    navController: NavHostController,
    sharedViewModel: SharedViewModel = viewModel(),
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
            sharedViewModel.navigate(IncrementScreens.Index)
            IncrementScreen()
        }
    }
}

@Composable
fun NavigatorScreenNavHost(
    navController: NavHostController,
    sharedViewModel: SharedViewModel = viewModel(),
) {
    NavHost(
        navController = navController,
        startDestination = NavigatorScreens.Index.route,
    ) {
        composable(
            route = NavigatorScreens.Index.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
        ) { backStackEntry ->
            sharedViewModel.navigate(NavigatorScreens.Page(1))
            NavigatorScreen(1, navController)
        }
        composable(
            route = navigatorScreensRouteFormat,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
        ) { backStackEntry ->
            val count = backStackEntry.arguments?.getString("count")!!.toInt()
            sharedViewModel.navigate(NavigatorScreens.Page(count))
            NavigatorScreen(count, navController)
        }
    }
}