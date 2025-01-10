package org.sopt.and.data.remote.datasource.remote

import org.sopt.and.data.api.AuthApi
import org.sopt.and.utils.base.NullableBaseResponse
import org.sopt.and.data.model.request.UserLoginRequestDto
import org.sopt.and.data.model.request.UserRegisterRequestDto
import org.sopt.and.data.model.response.UserLoginResponse
import org.sopt.and.data.model.response.UserRegisterResponse
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    private val authApi: AuthApi
) {
    suspend fun registerUser(userRegisterRequestDto: UserRegisterRequestDto): NullableBaseResponse<UserRegisterResponse> {
        return authApi.registerUser(userRegisterRequestDto)
    }

    suspend fun loginUser(userLoginRequestDto: UserLoginRequestDto): NullableBaseResponse<UserLoginResponse> {
        return authApi.loginUser(userLoginRequestDto)
    }
}