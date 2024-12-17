package org.sopt.and.presentation.ui.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.sopt.and.core.designsystem.theme.Black
import org.sopt.and.core.designsystem.theme.Gray100
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
                    tabs = MainBottomNavigationType.entries.toImmutableList(),
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
                .statusBarsPadding()
                .padding(bottom = paddingValues.calculateBottomPadding()), // 하단만 패딩 적용
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
                    navHostController = navigator.navHostController,
                )
                homeNavGraph(
                    navHostController = navigator.navHostController,
                )
                searchNavGraph(
                    navHostController = navigator.navHostController,
                )
                myPageNavGraph(
                    navHostController = navigator.navHostController,
                )
            }
        }
    }
}

@Composable
private fun MainBottomBar(
    tabs: ImmutableList<MainBottomNavigationType>,
    currentTab: MainBottomNavigationType,
    onTabSelected: (MainBottomNavigationType) -> Unit,
) {
    NavigationBar(containerColor = Black) {
        tabs.forEach { itemType ->
            val isSelected = currentTab == itemType

            NavigationBarItem(
                interactionSource = NoRippleInteraction,
                selected = isSelected,
                onClick = {
                    onTabSelected(itemType)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = itemType.icon),
                        contentDescription = stringResource(id = itemType.title),
                        tint = if (isSelected) White else Gray100
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = itemType.title),
                        color = if (isSelected) White else Gray100
                    )
                },
                colors =
                NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = White,
                    selectedTextColor = White,
                    unselectedIconColor = Gray100,
                    unselectedTextColor = Gray100
                ),
            )
        }
    }
}
