package org.sopt.and.data.api

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
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    // 구글 로그인 API
    @POST("/api/v1/auth/login")
    suspend fun postLogin(
        @Body body: GoogleLoginRequest
    ) : GoogleLoginResponse

    // 액세스 토큰 재발급 API
    @POST("/api/v1/auth/reissue")
    suspend fun postRefresh(
        @Body refreshRequest: RefreshRequest
    ) : Response<RefreshResponse>

    @POST("/user")
    suspend fun registerUser(
        @Body body: UserRegisterRequestDto
    ) : NullableBaseResponse<UserRegisterResponse>

    @POST("/login")
    suspend fun loginUser(
        @Body body: UserLoginRequestDto
    ) : NullableBaseResponse<UserLoginResponse>

}