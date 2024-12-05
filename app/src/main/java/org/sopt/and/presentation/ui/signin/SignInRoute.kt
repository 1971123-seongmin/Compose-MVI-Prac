package org.sopt.and.presentation.ui.signin

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.and.presentation.utils.contract.SignInContract
import org.sopt.and.presentation.viewmodel.SignInViewModel
import org.sopt.and.utils.showToastMessage

@Composable
fun SignInRoute (
    navigateSignUp: () -> Unit,
    navigateHome: () -> Unit,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val signInState by viewModel.uiState.collectAsStateWithLifecycle()
    val signInEffect = viewModel.effect
    val context = LocalContext.current

    LaunchedEffect(signInEffect) {
        signInEffect.collect {
            when(it) {
                is SignInContract.Effect.ShowToast -> {
                    context.showToastMessage(it.message)
                }
            }
        }
    }

    LaunchedEffect(signInState.loginStatus) {
        if (signInState.loginStatus == SignInContract.SignInStatus.SUCCESS) {
            navigateHome()
        }
    }

    Scaffold (
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        SignInScreen(
            navigateSignUp,
            signInName = signInState.username,
            signInPwd = signInState.password,
            onNameChange = { viewModel.setEvent(SignInContract.Event.OnUsernameChanged(it)) },
            onPwdChange = { viewModel.setEvent(SignInContract.Event.OnPasswordChanged(it)) },
            isPwdVisibility = signInState.isPassWordVisibility,
            isPwdVisible ={ viewModel.setEvent(SignInContract.Event.OnPasswordVisibilityToggle) },
            onSignInBtnClick = { viewModel.setEvent(SignInContract.Event.OnSignInButtonClicked) },
            modifier = Modifier.padding(innerPadding)
        )
    }

}