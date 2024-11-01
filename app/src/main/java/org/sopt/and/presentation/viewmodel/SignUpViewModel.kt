package org.sopt.and.presentation.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.and.GlobalApplication
import org.sopt.and.presentation.state.SignUpState
import org.sopt.and.utils.validation.isValidEmail
import org.sopt.and.utils.validation.isValidPassword
import java.util.regex.Pattern

class SignUpViewModel : ViewModel() {

    private val _signUpState = MutableStateFlow(SignUpState())
    val signUpState: StateFlow<SignUpState>
        get() =  _signUpState.asStateFlow()

    private val _isSignUpSuccess = MutableStateFlow(false) // 회원가입 성공 여부
    val isSignUpSuccess: StateFlow<Boolean>
        get() = _isSignUpSuccess.asStateFlow()

    val dataStore = GlobalApplication.getInstance().getDataStore()

    // 회원가입 처리 함수
    fun signUp() {
        viewModelScope.launch {
            val email = _signUpState.value.email
            val password = _signUpState.value.password

            if(email.isValidEmail() && password.isValidPassword()) {
                dataStore.saveEmail(email)
                dataStore.savePwd(password)
                _isSignUpSuccess.value = true
            } else {
                _isSignUpSuccess.value
            }
        }
    }

    // 비밀번호 표시 여부
    fun togglePasswordVisibility() {
        _signUpState.value = _signUpState.value.copy(
            isPassWordVisibility = !_signUpState.value.isPassWordVisibility
        )
    }

    // 이메일 입력 처리
    fun setSignUpEmail(newEmail: String) {
        _signUpState.value = _signUpState.value.copy(
            email = newEmail
        )
    }

    // 비밀번호 입력 처리
    fun setSignUpPwd(newPassword: String) {
        _signUpState.value = _signUpState.value.copy(
            password = newPassword
        )
    }

}