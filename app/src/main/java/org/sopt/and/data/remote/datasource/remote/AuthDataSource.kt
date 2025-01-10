package org.sopt.and.data.remote.datasource.remote

import org.sopt.and.utils.base.NullableBaseResponse
import org.sopt.and.data.model.request.UserLoginRequestDto
import org.sopt.and.data.model.request.UserRegisterRequestDto
import org.sopt.and.data.model.response.UserLoginResponse
import org.sopt.and.data.model.response.UserRegisterResponse

interface AuthDataSource {
    suspend fun registerUser(userRegisterRequestDto: UserRegisterRequestDto): NullableBaseResponse<UserRegisterResponse>
    suspend fun loginUser(userLoginRequestDto: UserLoginRequestDto) : NullableBaseResponse<UserLoginResponse>
}