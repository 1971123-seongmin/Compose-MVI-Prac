package org.sopt.and.presentation.ui.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.and.core.navigation.MainBottomNavigation

fun NavController.navigateHome(navOptions: NavOptions? = null) {
    navigate(
        route = MainBottomNavigation.HOME,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.homeNavGraph(
    navHostController: NavController,
) {
    composable<MainBottomNavigation.HOME> {
        HomeScreen()
    }
}