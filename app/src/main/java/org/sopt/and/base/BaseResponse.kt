package org.sopt.and.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    @SerialName("code")
    val code: String,
    @SerialName("message")
    val message: String,
    @SerialName("result")
    val result: T,
)

@Serializable
data class NullableBaseResponse<T>(
    @SerialName("code")
    val code: String? = null,
    @SerialName("message")
    val message: String? = null,
    val result: T,
)