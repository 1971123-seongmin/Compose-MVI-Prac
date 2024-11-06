package org.sopt.and.data.remote.source

import org.sopt.and.base.BaseResponse
import org.sopt.and.data.model.request.UserRegisterRequestDto
import org.sopt.and.data.model.response.UserRegisterResponse
import org.sopt.and.data.service.UserApi
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userApi: UserApi
) : UserDataSource {
    override suspend fun registerUser(
        userRegisterRequestDto: UserRegisterRequestDto): BaseResponse<UserRegisterResponse> =
        userApi.registerUser(userRegisterRequestDto)
}