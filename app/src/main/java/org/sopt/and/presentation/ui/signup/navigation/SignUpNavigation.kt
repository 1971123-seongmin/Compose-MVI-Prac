package org.sopt.and.presentation.ui.signup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.and.core.navigation.Route
import org.sopt.and.presentation.ui.signup.SignUpRoute

fun NavController.navigateSignUp(navOptions: NavOptions? = null) {
    navigate(
        route = SignUpRoute.REGISTER_ROUTE,
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.signUpGraph(
    navHostController: NavHostController,
) {
    composable(SignUpRoute.REGISTER_ROUTE){
        SignUpRoute(
            navigateSignIn = { navHostController.popBackStack() }
        )
    }
}

@Serializable
data object SignUpRoute {
    const val REGISTER_ROUTE = "register"
}