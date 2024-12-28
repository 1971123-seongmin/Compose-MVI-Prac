package org.sopt.and.core.navigation

import kotlinx.serialization.Serializable

sealed interface Route

sealed interface MainBottomNavigation : Route {
    @Serializable
    data object HOME : MainBottomNavigation

    @Serializable
    data object SEARCH : MainBottomNavigation

    @Serializable
    data object MY : MainBottomNavigation
}
