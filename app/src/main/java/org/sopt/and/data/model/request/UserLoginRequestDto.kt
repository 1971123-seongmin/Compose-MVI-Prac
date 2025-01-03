package org.sopt.and.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserLoginRequestDto(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String
)
