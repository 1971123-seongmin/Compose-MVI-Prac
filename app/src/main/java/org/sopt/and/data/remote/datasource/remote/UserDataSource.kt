package org.sopt.and.data.remote.datasource.remote

import org.sopt.and.data.api.UserApi
import org.sopt.and.utils.base.NullableBaseResponse
import org.sopt.and.data.model.response.MyHobbyResponse
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val userApi: UserApi
)  {
    suspend fun getMyHobby(): NullableBaseResponse<MyHobbyResponse> {
        return userApi.getMyHobby()
    }

}