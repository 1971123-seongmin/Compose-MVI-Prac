package org.sopt.and.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserLoginResponse(
    @SerialName("token")
    val token : String
)
