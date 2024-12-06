package org.sopt.and.presentation.ui.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.compose.NavHost
import org.sopt.and.core.designsystem.theme.Black
import org.sopt.and.core.designsystem.theme.MoreDarkGray
import org.sopt.and.core.designsystem.theme.White
import org.sopt.and.core.util.NoRippleInteraction
import org.sopt.and.presentation.ui.home.homeNavGraph
import org.sopt.and.presentation.ui.main.navigation.MainBottomNavigationType
import org.sopt.and.presentation.ui.main.navigation.MainNavigator
import org.sopt.and.presentation.ui.mypage.myPageNavGraph
import org.sopt.and.presentation.ui.search.searchNavGraph
import org.sopt.and.presentation.ui.signin.signInGraph
import org.sopt.and.presentation.ui.signup.navigation.signUpGraph

@Composable
fun MainScreen(
    navigator: MainNavigator,
) {
    Scaffold(
        bottomBar = {
            navigator.currentTab?.let {
                MainBottomBar(
                    tabs = MainBottomNavigationType.entries.toList(),
                    currentTab = it,
                    onTabSelected = { bottomNavi ->
                        navigator.navigateMainBottomNavigation((bottomNavi.route))
                    },
                )
            }
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
        ) {
            NavHost(
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                popEnterTransition = { EnterTransition.None },
                popExitTransition = { ExitTransition.None },
                navController = navigator.navHostController,
                startDestination = navigator.startDestination,
            ) {
                signInGraph(
                    navigateToRegister = { navigator.navigateSignUp() },
                    navigateToHome = { navigator.navigateSignInToHome() }
                )
                signUpGraph(
                    navHostController = navigator.navHostController
                )
                homeNavGraph(
                    navHostController = navigator.navHostController,
                )
                searchNavGraph()
                myPageNavGraph()
            }
        }
    }
}

@Composable
private fun MainBottomBar(
    tabs: List<MainBottomNavigationType>,
    currentTab: MainBottomNavigationType,
    onTabSelected: (MainBottomNavigationType) -> Unit,
) {
    NavigationBar(containerColor = Black) {
        tabs.forEach { itemType ->
            NavigationBarItem(
                interactionSource = NoRippleInteraction,
                selected = currentTab == itemType,
                onClick = {
                    onTabSelected(itemType)
                },
                icon = {
                    Image(
                        imageVector = ImageVector.vectorResource(currentTab.icon),
                        contentDescription = null,
                    )
                },
                label = {
                    Text(
                        stringResource(id = itemType.title),
                    )
                },
                colors =
                NavigationBarItemColors(
                    selectedIconColor = White,
                    selectedTextColor = White,
                    selectedIndicatorColor = White,
                    unselectedIconColor = MoreDarkGray,
                    unselectedTextColor = MoreDarkGray,
                    disabledTextColor = MoreDarkGray,
                    disabledIconColor = MoreDarkGray,
                ),
            )
        }
    }
}
