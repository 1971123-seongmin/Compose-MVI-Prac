package org.sopt.and.presentation.utils.contract

class SignInContract {

    data class SignInState(
        val email: String = "",
        val password: String = "",
        var isPassWordVisibility: Boolean = false,
        val loginStatus: Boolean? = null,
    )

}