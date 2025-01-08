package org.sopt.and.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import org.sopt.and.domain.usecase.google.GoogleLoginUseCase
import org.sopt.and.presentation.utils.contract.SignInContract
import org.sopt.and.utils.LoadState
import javax.inject.Inject

@HiltViewModel
class GoogleSignInViewModel @Inject constructor(
    private val googleLoginUseCase: GoogleLoginUseCase
) : ContainerHost<SignInContract.SignInState, SignInContract.SideEffect>, ViewModel() {

    override val container: Container<SignInContract.SignInState, SignInContract.SideEffect> =
        container(initialState = SignInContract.SignInState())

    fun googleLogin() = intent {
        // 상태를 IDLE로 초기화
        reduce {
            state.copy(
                loginStatus = LoadState.Loading
            )
        }

        // Google 로그인 유스케이스 호출
        googleLoginUseCase()
            .onSuccess { credential ->
                Log.d("GoogleSignIn", "Login successful: Credential ID = ${credential}")
                reduce {
                    state.copy(
                        loginStatus = LoadState.Success
                    )
                }
                // 성공 시 부수 효과로 토스트 메시지 표시
                postSideEffect(SignInContract.SideEffect.ShowToast("Login successful: ${credential}"))
            }
            .onFailure { exception ->
                Log.e("GoogleSignIn", "Login failed", exception)
                reduce {
                    state.copy(
                        loginStatus = LoadState.Error
                    )
                }
                // 실패 시 부수 효과로 에러 메시지 표시
                postSideEffect(SignInContract.SideEffect.ShowToast("Login failed: ${exception.message}"))
            }
    }

    fun onUsernameChanged(username: String) = intent {
        reduce {
            state.copy(username = username)
        }
    }

    fun onPasswordChanged(password: String) = intent {
        reduce {
            state.copy(password = password)
        }
    }

    fun onPasswordVisibilityToggle() = intent {
        reduce {
            state.copy(isPassWordVisibility = !state.isPassWordVisibility)
        }
    }
}
