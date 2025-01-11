package org.sopt.and.data.model.response

import org.sopt.and.domain.model.AuthToken

data class RefreshResponse(
    //@SerializedName("accessToken")
    val accessToken: String,
    //@SerializedName("refreshToken")
    val refreshToken: String,
) {
    // 리프레시 토큰 -> 새로운 토큰으로 갱신하기 위해 변환하는 함수
    fun refreshResponseToAuthToken() =
        AuthToken(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
}
