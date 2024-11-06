package org.sopt.and.data.remote.source

import org.sopt.and.base.BaseResponse
import org.sopt.and.data.model.request.UserRegisterRequestDto
import org.sopt.and.data.model.response.UserRegisterResponse

interface UserDataSource {
    suspend fun registerUser(userRegisterRequestDto: UserRegisterRequestDto): BaseResponse<UserRegisterResponse>
}