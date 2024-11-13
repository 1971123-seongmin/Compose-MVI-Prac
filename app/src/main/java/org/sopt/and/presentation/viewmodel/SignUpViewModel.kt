package org.sopt.and.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.base.BaseViewModel
import org.sopt.and.domain.model.auth.RegisterUserEntity
import org.sopt.and.domain.usecase.RegisterUserUseCase
import org.sopt.and.presentation.utils.UiEffect
import org.sopt.and.presentation.utils.UiEvent
import org.sopt.and.presentation.utils.contract.SignUpContract
import org.sopt.and.utils.validation.isValidLength
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authUserCase: RegisterUserUseCase
) : BaseViewModel<SignUpContract.Event, SignUpContract.SignUpState, SignUpContract.Effect>() {

    override fun createInitialState(): SignUpContract.SignUpState {
        return SignUpContract.SignUpState()
    }

    override fun handleEvent(event: UiEvent) {
        when (event) {
            is SignUpContract.Event.OnUsernameChanged -> {
                setState(currentState.copy(username = event.username))
            }

            is SignUpContract.Event.OnPasswordChanged -> {
                setState(currentState.copy(password = event.password))
            }

            is SignUpContract.Event.OnHobbyChanged -> {
                setState(currentState.copy(hobby = event.hobby))
            }

            is SignUpContract.Event.OnPasswordVisibilityToggle -> {
                setState(currentState.copy(isPassWordVisibility = !currentState.isPassWordVisibility))
            }

            is SignUpContract.Event.OnSignUpButtonClicked -> {
                val isUsernameValid = currentState.username.isValidLength()
                val isPasswordValid = currentState.password.isValidLength()
                val isHobbyValid = currentState.hobby.isValidLength()

                when {
                    !isUsernameValid -> {
                        setState(currentState.copy(status = SignUpContract.SignUpStatus.FAILURE))
                        setEffect(SignUpContract.Effect.ShowToast("이름이 8자 이상입니다!."))
                    }
                    !isPasswordValid -> {
                        setState(currentState.copy(status = SignUpContract.SignUpStatus.FAILURE))
                        setEffect(SignUpContract.Effect.ShowToast("비밀번호가 8자 이상입니다!"))
                    }
                    !isHobbyValid -> {
                        setState(currentState.copy(status = SignUpContract.SignUpStatus.FAILURE))
                        setEffect(SignUpContract.Effect.ShowToast("취미가 8자 이상입니다!"))
                    }
                    else -> {
                        viewModelScope.launch {
                            val result = authUserCase(
                                RegisterUserEntity(
                                    currentState.username,
                                    currentState.password,
                                    currentState.hobby
                                )
                            )
                            result.onSuccess {
                                setState(currentState.copy(status = SignUpContract.SignUpStatus.SUCCESS))
                                setEffect(SignUpContract.Effect.ShowToast("회원가입 성공"))
                            }.onFailure { exception ->
                                setState(currentState.copy(status = SignUpContract.SignUpStatus.FAILURE))
                                setEffect(SignUpContract.Effect.ShowToast("회원가입 실패: ${exception.message}"))
                            }
                        }
                    }
                }
            }
        }
    }

    override fun handleEffect(effect: UiEffect) { }

}