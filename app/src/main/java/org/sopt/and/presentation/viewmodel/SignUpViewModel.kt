package org.sopt.and.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.utils.base.BaseViewModel
import org.sopt.and.domain.model.auth.RegisterUserEntity
import org.sopt.and.domain.usecase.auth.RegisterUserUseCase
import org.sopt.and.presentation.utils.contract.SignUpContract
import org.sopt.and.utils.isValidLength
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authUserCase: RegisterUserUseCase
) : BaseViewModel<SignUpContract.SignUpState, SignUpContract.SideEffect, SignUpContract.Event>() {

    override fun createInitialState(): SignUpContract.SignUpState {
        return SignUpContract.SignUpState()
    }

    override fun handleEvent(event: SignUpContract.Event) {
        when (event) {
            is SignUpContract.Event.OnUsernameChanged -> {
                setState { copy(username = event.username) }
            }

            is SignUpContract.Event.OnPasswordChanged -> {
                setState { copy(password = event.password) }
            }

            is SignUpContract.Event.OnHobbyChanged -> {
                setState { copy(hobby = event.hobby) }
            }

            is SignUpContract.Event.OnPasswordVisibilityToggle -> {
                setState { copy(isPassWordVisibility = !currentState.isPassWordVisibility) }
            }

            is SignUpContract.Event.OnSignUpButtonClicked -> {
                val isUsernameValid = currentState.username.isValidLength()
                val isPasswordValid = currentState.password.isValidLength()
                val isHobbyValid = currentState.hobby.isValidLength()

                when {
                    !isUsernameValid -> {
                        setState { copy(status = SignUpContract.SignUpStatus.FAILURE) }
                        setSideEffect(SignUpContract.SideEffect.ShowToast("이름이 8자 이상입니다!."))
                    }
                    !isPasswordValid -> {
                        setState { copy(status = SignUpContract.SignUpStatus.FAILURE) }
                        setSideEffect(SignUpContract.SideEffect.ShowToast("비밀번호가 8자 이상입니다!"))
                    }
                    !isHobbyValid -> {
                        setState { copy(status = SignUpContract.SignUpStatus.FAILURE) }
                        setSideEffect(SignUpContract.SideEffect.ShowToast("취미가 8자 이상입니다!"))
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
                                setState { copy(status = SignUpContract.SignUpStatus.SUCCESS) }
                                setSideEffect(SignUpContract.SideEffect.ShowToast("회원가입 성공"))
                            }.onFailure { exception ->
                                setState { copy(status = SignUpContract.SignUpStatus.FAILURE) }
                                setSideEffect(SignUpContract.SideEffect.ShowToast("회원가입 실패: ${exception.message}"))
                            }
                        }
                    }
                }
            }
        }
    }

}