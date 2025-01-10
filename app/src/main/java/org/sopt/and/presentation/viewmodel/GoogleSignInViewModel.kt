package org.sopt.and.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import org.sopt.and.domain.repository.GoogleSignInRepository
import org.sopt.and.presentation.utils.contract.SignInContract
import javax.inject.Inject

@HiltViewModel
class GoogleSignInViewModel @Inject constructor(

) : ContainerHost<SignInContract.SignInState, SignInContract.SideEffect>, ViewModel() {
    override val container: Container<SignInContract.SignInState, SignInContract.SideEffect> =
        container(initialState = SignInContract.SignInState())

    // orbit에서 이런식으로 함수를 구성하는게 맞을까요??
    // 구글로그인 함수 요청 외에 상태를 바꾸는 부분, 사이드 이펙트를 정의하는 부분이 함께 있어서 잘못된 것 같습니다
//    fun googleLogin(googleSignInRepository: GoogleSignInRepository) = intent {
//        // 상태를 IDLE로 초기화
//        reduce {
//            state.copy(
//                loginStatus = LoadState.Loading
//            )
//        }
//
//        // Google 로그인 서버에 요청을 보냄
//        googleSignInRepository.signIn()
//            .onSuccess { credential ->
//                reduce {
//                    state.copy(
//                        loginStatus = LoadState.Success
//                    )
//                }
//                // 성공 시 부수 효과로 토스트 메시지 표시
//                postSideEffect(SignInContract.SideEffect.ShowToast("Login successful: ${credential}"))
//            }
//            .onFailure { exception ->
//                reduce {
//                    state.copy(
//                        loginStatus = LoadState.Error
//                    )
//                }
//                // 실패 시 부수 효과로 에러 메시지 표시
//                postSideEffect(SignInContract.SideEffect.ShowToast("Login failed: ${exception.message}"))
//            }
//    }

    // 구글 로그인 임시 액티비티 실행되도록 하는 목적의 함수
    fun googleLogin(googleSignInRepository: GoogleSignInRepository) {
        viewModelScope.launch {
            googleSignInRepository.signIn()
        }
    }
}
