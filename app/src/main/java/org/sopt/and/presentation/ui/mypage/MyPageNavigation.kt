package org.sopt.and.presentation.ui.mypage

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.and.core.navigation.MainBottomNavigation

fun NavController.navigateMyPage(navOptions: NavOptions? = null) {
    navigate(
        route = MainBottomNavigation.MY,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.myPageNavGraph(
) {
    composable<MainBottomNavigation.MY> {
        MyPageRoute()
    }
}