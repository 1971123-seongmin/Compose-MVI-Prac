package org.sopt.and.data.remote.datasource.remote

import org.sopt.and.data.api.AuthApi
import org.sopt.and.data.model.request.GoogleLoginRequest
import org.sopt.and.data.model.request.RefreshRequest
import org.sopt.and.utils.base.NullableBaseResponse
import org.sopt.and.data.model.request.UserLoginRequestDto
import org.sopt.and.data.model.request.UserRegisterRequestDto
import org.sopt.and.data.model.response.GoogleLoginResponse
import org.sopt.and.data.model.response.RefreshResponse
import org.sopt.and.data.model.response.UserLoginResponse
import org.sopt.and.data.model.response.UserRegisterResponse
import retrofit2.Response
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    private val authApi: AuthApi
) {
    // 구글 로그인
    suspend fun postLogin(googleLoginRequest: GoogleLoginRequest): GoogleLoginResponse {
        return authApi.postLogin(googleLoginRequest)
    }
    // refresh Token
    suspend fun postRefresh(refreshRequest: RefreshRequest): Response<RefreshResponse> {
        return authApi.postRefresh(refreshRequest)
    }

    suspend fun registerUser(userRegisterRequestDto: UserRegisterRequestDto): NullableBaseResponse<UserRegisterResponse> {
        return authApi.registerUser(userRegisterRequestDto)
    }

    suspend fun loginUser(userLoginRequestDto: UserLoginRequestDto): NullableBaseResponse<UserLoginResponse> {
        return authApi.loginUser(userLoginRequestDto)
    }

}