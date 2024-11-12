package org.sopt.and.data.remote.source

import org.sopt.and.base.NullableBaseResponse
import org.sopt.and.data.model.request.UserRegisterRequestDto
import org.sopt.and.data.model.response.UserRegisterResponse

interface AuthDataSource {
    suspend fun registerUser(userRegisterRequestDto: UserRegisterRequestDto): NullableBaseResponse<UserRegisterResponse>
}