package org.sopt.and.domain.model.auth

data class RegisterUserEntity(
    val username: String,
    val password: String,
    val hobby: String,
)
