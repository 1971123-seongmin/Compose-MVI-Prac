package org.sopt.and.data.model.response

import kotlinx.serialization.SerialName

data class UserRegisterResponse (
    @SerialName("no")
    val userId : Long
)