package org.sopt.and.presentation.ui.signin

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.and.domain.repository.GoogleTokenRepository

fun NavController.navigateSignIn(navOptions: NavOptions? = null) {
    navigate(
        route = SignInRoute.LOGIN_ROUTE,
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.signInGraph(
    navigateToRegister: () -> Unit,
    navigateToHome: () -> Unit,
    googleSignInRepository: GoogleTokenRepository
) {
    composable(SignInRoute.LOGIN_ROUTE) {
        SignInRoute(
            navigateSignUp = navigateToRegister ,
            navigateHome = navigateToHome ,
            googleSignInRepository = googleSignInRepository
        )
    }
}

@Serializable
data object SignInRoute {
    const val LOGIN_ROUTE = "login"
}