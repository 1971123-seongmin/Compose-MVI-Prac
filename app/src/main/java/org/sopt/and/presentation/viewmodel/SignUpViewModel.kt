package org.sopt.and.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.base.BaseViewModel
import org.sopt.and.domain.model.user.RegisterUserEntity
import org.sopt.and.domain.usecase.RegisterUserUseCase
import org.sopt.and.presentation.utils.UiEffect
import org.sopt.and.presentation.utils.UiEvent
import org.sopt.and.presentation.utils.contract.SignUpContract
import org.sopt.and.utils.validation.isValidLength
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userUseCase: RegisterUserUseCase
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
                if (currentState.username.isValidLength() && currentState.password.isValidLength() &&
                    currentState.hobby.isValidLength()
                ) {
                    viewModelScope.launch {
                        val result = userUseCase(
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
                            Log.d("실패", "${exception.message}")
                        }
                    }
                }
            }
        }
    }

    override fun handleEffect(effect: UiEffect) { }

}