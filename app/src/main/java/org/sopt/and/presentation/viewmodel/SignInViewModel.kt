package org.sopt.and.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.domain.model.auth.LoginUserEntity
import org.sopt.and.domain.usecase.auth.LoginUserUseCase
import org.sopt.and.presentation.utils.contract.SignInContract
import org.sopt.and.utils.base.BaseViewModel
import org.sopt.and.utils.isValidLength
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authUserCase: LoginUserUseCase
) : BaseViewModel<SignInContract.SignInState, SignInContract.SideEffect, SignInContract.Event>() {

    override fun createInitialState(): SignInContract.SignInState {
        return SignInContract.SignInState()
    }

    override fun handleEvent(event: SignInContract.Event) {
        when (event) {
            is SignInContract.Event.OnUsernameChanged -> {
                Log.d("ssssf", "Username Updated: ${event.username}")
                setState { copy(username = event.username) }
            }
            is SignInContract.Event.OnPasswordChanged -> {
                setState { copy(password = event.password) }
            }
            is SignInContract.Event.OnPasswordVisibilityToggle -> {
                setState { copy(isPassWordVisibility = !currentState.isPassWordVisibility) }
            }
            is SignInContract.Event.OnSignInButtonClicked -> {
                val isUserNameValid = currentState.username.isValidLength()
                val isPasswordValid = currentState.password.isValidLength()

                when {
                    !isUserNameValid -> {
                        setState { copy(loginStatus = SignInContract.SignInStatus.FAILURE) }
                        setSideEffect(SignInContract.SideEffect.ShowToast("이름이 8자 이상입니다!"))
                    }
                    !isPasswordValid -> {
                        setState { copy(loginStatus = SignInContract.SignInStatus.FAILURE) }
                        setSideEffect(SignInContract.SideEffect.ShowToast("비밀번호가 8자 이상입니다!"))
                    }
                    else -> {
                        viewModelScope.launch {
                            val result = authUserCase(
                                LoginUserEntity(
                                    currentState.username,
                                    currentState.password
                                )
                            )
                            result.onSuccess {
                                setState { copy(loginStatus = SignInContract.SignInStatus.SUCCESS) }
                                setSideEffect(SignInContract.SideEffect.ShowToast("로그인 성공"))

                            }.onFailure { exception ->
                                setState { copy(loginStatus = SignInContract.SignInStatus.FAILURE) }
                                setSideEffect(SignInContract.SideEffect.ShowToast("로그인 실패: ${exception.message}"))
                            }
                        }
                    }
                }
            }
        }
    }

}