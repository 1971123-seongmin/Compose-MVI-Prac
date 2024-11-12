package org.sopt.and.presentation.utils.contract

import org.sopt.and.presentation.utils.UiEffect
import org.sopt.and.presentation.utils.UiEvent
import org.sopt.and.presentation.utils.UiState

class SignInContract {

   sealed class Event : UiEvent {
       data class OnUsernameChanged(val username: String) : Event()
       data class OnPasswordChanged(val password: String) : Event()
       data object OnPasswordVisibilityToggle : Event()
       data object OnSignInButtonClicked : Event()
   }

    enum class SignInStatus {
        IDLE, SUCCESS, FAILURE
    }

    data class SignInState(
        val username: String = "",
        val password: String = "",
        var isPassWordVisibility: Boolean = false,
        val loginStatus: SignInStatus = SignInStatus.IDLE
    ) : UiState

    sealed class Effect : UiEffect {
        data class ShowToast(val message: String) : Effect()
    }

}