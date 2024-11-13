package org.sopt.and.data.remote.source.auth

import org.sopt.and.base.NullableBaseResponse
import org.sopt.and.data.model.request.UserLoginRequestDto
import org.sopt.and.data.model.request.UserRegisterRequestDto
import org.sopt.and.data.model.response.UserLoginResponse
import org.sopt.and.data.model.response.UserRegisterResponse
import org.sopt.and.data.service.AuthApi
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthDataSource {
    override suspend fun registerUser(
        userRegisterRequestDto: UserRegisterRequestDto): NullableBaseResponse<UserRegisterResponse> =
        authApi.registerUser(userRegisterRequestDto)

    override suspend fun loginUser(userLoginRequestDto: UserLoginRequestDto): NullableBaseResponse<UserLoginResponse> =
        authApi.loginUser(userLoginRequestDto)
}