package org.sopt.and.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.sopt.and.R

sealed class BottomNavItem(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val screenRoute: String
) {
    data object Home : BottomNavItem(
        title = R.string.bottom_home_title,
        icon = R.drawable.ic_navi_home_24dp,
        screenRoute  = Routes.HomeScreen.route
    )

    data object Search : BottomNavItem(
        title = R.string.bottom_search_title,
        icon = R.drawable.ic_navi_search_24dp,
        screenRoute  = Routes.SearchScreen.route
    )

    data object MyPage : BottomNavItem(
        title = R.string.bottom_my_title,
        icon = R.drawable.ic_navi_profile,
        screenRoute  = Routes.MyPageScreen.route
    )
}