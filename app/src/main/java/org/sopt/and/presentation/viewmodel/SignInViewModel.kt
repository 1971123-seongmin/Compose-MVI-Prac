package org.sopt.and.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.base.BaseViewModel
import org.sopt.and.domain.model.auth.LoginUserEntity
import org.sopt.and.domain.usecase.auth.LoginUserUseCase
import org.sopt.and.presentation.utils.UiEffect
import org.sopt.and.presentation.utils.UiEvent
import org.sopt.and.presentation.utils.contract.SignInContract
import org.sopt.and.utils.validation.isValidLength
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authUserCase: LoginUserUseCase
) : BaseViewModel<SignInContract.Event, SignInContract.SignInState, SignInContract.Effect>() {

    override fun createInitialState(): SignInContract.SignInState {
        return SignInContract.SignInState()
    }

    override fun handleEvent(event: UiEvent) {
        when (event) {
            is SignInContract.Event.OnUsernameChanged -> {
                setState(currentState.copy(username = event.username))
            }
            is SignInContract.Event.OnPasswordChanged -> {
                setState(currentState.copy(password = event.password))
            }
            is SignInContract.Event.OnPasswordVisibilityToggle -> {
                setState(currentState.copy(isPassWordVisibility = !currentState.isPassWordVisibility))
            }
            is SignInContract.Event.OnSignInButtonClicked -> {
                val isUserNameValid = currentState.username.isValidLength()
                val isPasswordValid = currentState.password.isValidLength()

                when {
                    !isUserNameValid -> {
                        setState(currentState.copy(loginStatus = SignInContract.SignInStatus.FAILURE))
                        setEffect(SignInContract.Effect.ShowToast("이름이 8자 이상입니다!"))
                    }
                    !isPasswordValid -> {
                        setState(currentState.copy(loginStatus = SignInContract.SignInStatus.FAILURE))
                        setEffect(SignInContract.Effect.ShowToast("비밀번호가 8자 이상입니다!"))
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
                                setState(currentState.copy(loginStatus = SignInContract.SignInStatus.SUCCESS))
                                setEffect(SignInContract.Effect.ShowToast("로그인 성공"))

                            }.onFailure { exception ->
                                setState(currentState.copy(loginStatus = SignInContract.SignInStatus.FAILURE))
                                setEffect(SignInContract.Effect.ShowToast("로그인 실패: ${exception.message}"))
                            }
                        }
                    }
                }
            }
        }
    }

    override fun handleEffect(effect: UiEffect) { }

}