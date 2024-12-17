package org.sopt.and.presentation.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import org.sopt.and.core.navigation.MainBottomNavigation
import org.sopt.and.presentation.ui.home.navigateHome
import org.sopt.and.presentation.ui.mypage.navigateMyPage
import org.sopt.and.presentation.ui.search.navigateSearch
import org.sopt.and.presentation.ui.signin.SignInRoute
import org.sopt.and.presentation.ui.signin.navigateSignIn
import org.sopt.and.presentation.ui.signup.navigation.navigateSignUp

class MainNavigator(
    val navHostController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() =
            navHostController
                .currentBackStackEntryAsState().value?.destination

    val startDestination = SignInRoute.LOGIN_ROUTE

    val currentTab: MainBottomNavigationType?
        @Composable get() =
            MainBottomNavigationType.find { bottomNavi ->
                currentDestination?.hasRoute(bottomNavi::class) == true
            }

    fun navigateMainBottomNavigation(bottomNavi: MainBottomNavigation) {
        navOptions {
            popUpTo(MainBottomNavigation.HOME::class.simpleName.orEmpty()) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }.let { navOptions ->
            when (bottomNavi) {
                MainBottomNavigation.HOME -> navHostController.navigateHome(navOptions)
                MainBottomNavigation.SEARCH -> navHostController.navigateSearch(navOptions)
                MainBottomNavigation.MY -> navHostController.navigateMyPage(navOptions)
            }
        }
    }

    fun navigateSignInToHome(navOptions: NavOptions? = null) {
        navHostController.navigateHome(
            navOptions ?: navOptions {
                popUpTo(navHostController.graph.findStartDestination().id) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        )
    }

    fun navigateSignIn() {
        navHostController.navigateSignIn()
    }

    fun navigateSignUp() {
        navHostController.navigateSignUp()
    }

    @Composable
    fun showBottomBar() =
        MainBottomNavigationType.contains {
            currentDestination?.hasRoute(it::class) == true
        }
}

@Composable
fun rememberMainNavigator(
    navHostController: NavHostController = rememberNavController(),
): MainNavigator = remember(navHostController) {
    MainNavigator(navHostController)
}