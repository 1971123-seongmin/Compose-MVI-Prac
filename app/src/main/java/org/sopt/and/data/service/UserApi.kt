package org.sopt.and.data.service

import org.sopt.and.base.NullableBaseResponse
import org.sopt.and.data.model.response.MyHobbyResponse
import retrofit2.http.GET
import retrofit2.http.PUT

interface UserApi {

    @GET("/user/my-hobby")
    suspend fun getMyHobby() : NullableBaseResponse<MyHobbyResponse>

    @GET("/user/{no}/my-hobby")
    suspend fun getOthersHobby(

    )

    @PUT("/user")
    suspend fun editUserInfo(

    )
}