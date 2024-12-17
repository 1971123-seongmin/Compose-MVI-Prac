package org.sopt.and.data.remote.source.user

import org.sopt.and.utils.base.NullableBaseResponse
import org.sopt.and.data.model.response.MyHobbyResponse
import org.sopt.and.data.service.UserApi
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userApi: UserApi
) : UserDataSource {
    override suspend fun getMyHobby(): NullableBaseResponse<MyHobbyResponse> =
        userApi.getMyHobby()
}