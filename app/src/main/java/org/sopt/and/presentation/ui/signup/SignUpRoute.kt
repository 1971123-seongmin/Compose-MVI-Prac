package org.sopt.and.presentation.ui.signup

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.and.presentation.viewmodel.SignUpViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.and.presentation.utils.contract.SignUpContract
import org.sopt.and.utils.showToastMessage

@Composable
fun SignUpRoute(
    navigateSignIn: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val signUpState by viewModel.uiState.collectAsStateWithLifecycle()
    val signupSideEffect = viewModel.sideEffect
    val context = LocalContext.current

    LaunchedEffect(signupSideEffect) {
        signupSideEffect.collect { sideEffect ->
            when(sideEffect) {
                is SignUpContract.SideEffect.ShowToast -> {
                    context.showToastMessage(sideEffect.message)
                }
            }
        }
    }

    LaunchedEffect(signUpState.status) {
        if (signUpState.status == SignUpContract.SignUpStatus.SUCCESS) {
            navigateSignIn()
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        SignUpScreen(
        signUpName = signUpState.username,
        signUpPwd = signUpState.password,
        signUpHobby = signUpState.hobby,
        onNameChange = { viewModel.setEvent(SignUpContract.Event.OnUsernameChanged(it)) },
        onPwdChange = { viewModel.setEvent(SignUpContract.Event.OnPasswordChanged(it)) },
        onHobbyChange = { viewModel.setEvent(SignUpContract.Event.OnHobbyChanged(it)) },
        isPwdVisibility = signUpState.isPassWordVisibility,
        isPwdVisible ={ viewModel.setEvent(SignUpContract.Event.OnPasswordVisibilityToggle) },
        onSignUpBtnClick = { viewModel.setEvent(SignUpContract.Event.OnSignUpButtonClicked) },
        navigateSignIn = navigateSignIn,
        modifier = Modifier.padding(innerPadding)
        )
    }

}