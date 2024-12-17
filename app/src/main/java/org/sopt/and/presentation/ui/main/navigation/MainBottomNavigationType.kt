package org.sopt.and.presentation.ui.main.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import org.sopt.and.R
import org.sopt.and.core.navigation.MainBottomNavigation
import org.sopt.and.core.navigation.Route

enum class MainBottomNavigationType(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val route: MainBottomNavigation,
) {
    HOME(
        title = R.string.bottom_home_title,
        icon = R.drawable.ic_navi_home_24dp,
        route = MainBottomNavigation.HOME,
    ),
    SEARCH(
        title = R.string.bottom_search_title,
        icon = R.drawable.ic_navi_search_24dp,
        route = MainBottomNavigation.SEARCH,
    ),
    MyPAGE(
        title = R.string.bottom_my_title,
        icon = R.drawable.ic_navi_profile,
        route = MainBottomNavigation.MY,
    ), ;

    companion object {
        @Composable
        fun find(predicate: @Composable (MainBottomNavigation) -> Boolean): MainBottomNavigationType? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}