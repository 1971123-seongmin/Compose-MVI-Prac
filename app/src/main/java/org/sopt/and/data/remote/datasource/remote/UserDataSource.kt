package org.sopt.and.data.remote.datasource.remote

import org.sopt.and.utils.base.NullableBaseResponse
import org.sopt.and.data.model.response.MyHobbyResponse

interface UserDataSource {
    suspend fun getMyHobby(): NullableBaseResponse<MyHobbyResponse>
}