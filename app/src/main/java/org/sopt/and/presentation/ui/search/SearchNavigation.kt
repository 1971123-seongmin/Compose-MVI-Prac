package org.sopt.and.presentation.ui.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.and.core.navigation.MainBottomNavigation

fun NavController.navigateSearch(navOptions: NavOptions? = null) {
    navigate(
        route = MainBottomNavigation.SEARCH,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.searchNavGraph(
    navHostController: NavHostController
) {
    composable<MainBottomNavigation.SEARCH> {
        SearchScreen(navHostController)
    }
}