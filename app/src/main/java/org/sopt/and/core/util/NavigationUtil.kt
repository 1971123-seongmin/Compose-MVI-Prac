package org.sopt.and.core.util

import androidx.navigation.NavController
import androidx.navigation.navOptions
import org.sopt.and.core.navigation.MainBottomNavigation

// 뒤로가기 시 모든 백스택 제거하고 함으로 이동
fun NavController.clearBackStackNavigateToHome() {
    this.navigate(
        route = MainBottomNavigation.HOME,
        navOptions = navOptions {
            popUpTo(route = MainBottomNavigation.HOME) {
                inclusive = true
            }
            launchSingleTop = true
        }
    )
}