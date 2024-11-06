package org.sopt.and.data.model.request

import kotlinx.serialization.SerialName

data class UserRegisterRequestDto (
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String,
    @SerialName("hobby")
    val hobby: String,
)