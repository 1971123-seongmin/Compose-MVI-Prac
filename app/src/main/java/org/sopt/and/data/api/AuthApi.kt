package org.sopt.and.data.api

import org.sopt.and.utils.base.NullableBaseResponse
import org.sopt.and.data.model.request.UserLoginRequestDto
import org.sopt.and.data.model.request.UserRegisterRequestDto
import org.sopt.and.data.model.response.UserLoginResponse
import org.sopt.and.data.model.response.UserRegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/user")
    suspend fun registerUser(
        @Body body: UserRegisterRequestDto
    ) : NullableBaseResponse<UserRegisterResponse>

    @POST("/login")
    suspend fun loginUser(
        @Body body: UserLoginRequestDto
    ) : NullableBaseResponse<UserLoginResponse>

}