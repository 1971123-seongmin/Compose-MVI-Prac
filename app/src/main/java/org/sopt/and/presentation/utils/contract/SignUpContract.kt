package org.sopt.and.presentation.utils.contract

import org.sopt.and.presentation.utils.UiSideEffect
import org.sopt.and.presentation.utils.UiEvent
import org.sopt.and.presentation.utils.UiState

class SignUpContract {

    // Events that user performed
    sealed class Event : UiEvent {
        data class OnUsernameChanged(val username: String) : Event()
        data class OnPasswordChanged(val password: String) : Event()
        data class OnHobbyChanged(val hobby: String) : Event()
        data object OnPasswordVisibilityToggle : Event()
        data object OnSignUpButtonClicked : Event()
    }

    // Possible states for SignUp
    enum class SignUpStatus {
        IDLE, SUCCESS, FAILURE
    }

    data class SignUpState (
        val username: String = "",
        val password: String = "",
        val hobby: String = "",
        var isPassWordVisibility: Boolean = false,
        val status: SignUpStatus = SignUpStatus.IDLE
    ) : UiState

    // Side effects
    sealed class SideEffect : UiSideEffect {
        data class ShowToast(val message: String) : SideEffect()
    }

}