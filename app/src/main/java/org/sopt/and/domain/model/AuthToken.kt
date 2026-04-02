package org.sopt.and.domain.model

data class AuthToken(
    val accessToken: String,
    val refreshToken: String,
)
