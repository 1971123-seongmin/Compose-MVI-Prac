package org.sopt.and.data.remote.source.user

import org.sopt.and.base.NullableBaseResponse
import org.sopt.and.data.model.response.MyHobbyResponse

interface UserDataSource {
    suspend fun getMyHobby(): NullableBaseResponse<MyHobbyResponse>
}