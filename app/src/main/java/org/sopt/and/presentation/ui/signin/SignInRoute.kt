package org.sopt.and.presentation.ui.signin

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.and.domain.repository.GoogleSignInRepository
import org.sopt.and.presentation.utils.contract.SignInContract
import org.sopt.and.presentation.viewmodel.GoogleSignInViewModel
import org.sopt.and.presentation.viewmodel.SignInViewModel
import org.sopt.and.utils.LoadState
import org.sopt.and.utils.showToastMessage

@Composable
fun SignInRoute (
    navigateSignUp: () -> Unit,
    navigateHome: () -> Unit,
    googleSignInRepository: GoogleSignInRepository,
    viewModel: SignInViewModel = hiltViewModel(),
    googleSignInViewModel: GoogleSignInViewModel = hiltViewModel() // GoogleSignInViewModel 추가
) {
    val signInState by viewModel.uiState.collectAsStateWithLifecycle()
    val signInSideEffect = viewModel.sideEffect
    val context = LocalContext.current

    val googleSignInState by googleSignInViewModel.container.stateFlow.collectAsState()
    val googleSignInSideEffect = googleSignInViewModel.container.sideEffectFlow

    // Google 로그인 SideEffect 처리
    LaunchedEffect(googleSignInSideEffect) {
        googleSignInSideEffect.collect { sideEffect ->
            when (sideEffect) {
                is SignInContract.SideEffect.ShowToast -> {
                    context.showToastMessage(sideEffect.message)
                }
            }
        }
    }

    LaunchedEffect(signInSideEffect) {
        signInSideEffect.collect { sideEffect ->
            when(sideEffect) {
                is SignInContract.SideEffect.ShowToast -> {
                    context.showToastMessage(sideEffect.message)
                }
            }
        }
    }

    LaunchedEffect(signInState.loginStatus) {
        if (signInState.loginStatus == LoadState.Success || googleSignInState.loginStatus == LoadState.Success) {
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
            onGoogleLoginClick = {
                googleSignInViewModel.googleLogin(googleSignInRepository)
            },
            modifier = Modifier.padding(innerPadding)
        )
    }

}