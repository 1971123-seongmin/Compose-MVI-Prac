package org.sopt.and.data.model.response

data class GoogleLoginResponse(
    //@SerializedName("accessToken")
    val accessToken: String,
    //@SerializedName("refreshToken")
    val refreshToken: String,
)
