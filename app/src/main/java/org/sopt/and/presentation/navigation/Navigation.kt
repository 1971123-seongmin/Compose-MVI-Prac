package org.sopt.and.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.sopt.and.presentation.ui.home.HomeScreen
import org.sopt.and.presentation.ui.mypage.MyPageScreen
import org.sopt.and.presentation.ui.search.SearchScreen
import org.sopt.and.presentation.ui.signin.SignInRoute
import org.sopt.and.presentation.ui.signin.SignInScreen
import org.sopt.and.presentation.ui.signup.SignUpRoute
import org.sopt.and.presentation.ui.signup.SignUpScreen
import org.sopt.and.presentation.viewmodel.SignInViewModel
import org.sopt.and.presentation.viewmodel.SignUpViewModel

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
   Scaffold (
       modifier = Modifier.fillMaxSize()
   ) {

       NavHost(
           navController = navController,
           startDestination = Routes.SignInScreen.route
       ) {
           composable(Routes.SignInScreen.route) {
                SignInRoute(
                    navigateSignUp = { navController.navigate(Routes.SignUpScreen.route) },
                    navigateMyPage = { navController.navigate(Routes.MyPageScreen.route) },
                )
           }

           composable(Routes.SignUpScreen.route) {
               SignUpRoute(
                   navigateSignIn = { navController.navigate(Routes.SignInScreen.route) }
               )
           }

           composable(
               Routes.MyPageScreen.route,
               arguments = listOf(navArgument("email") { type = NavType.StringType })
           ) { backStackEntry ->
               val email = backStackEntry.arguments?.getString("email") ?: ""
               MyPageScreen(email = email)
           }

           composable(Routes.SearchScreen.route) {
               SearchScreen()
           }

           composable(Routes.HomeScreen.route) {
               HomeScreen()
           }
       }
   }

}
