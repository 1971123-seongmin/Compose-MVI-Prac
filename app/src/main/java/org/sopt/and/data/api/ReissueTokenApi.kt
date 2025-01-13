package org.sopt.and.data.api

import org.sopt.and.data.model.request.RefreshRequest
import org.sopt.and.data.model.response.RefreshResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ReissueTokenApi {
    // 액세스 토큰 재발급 API
    @POST("/api/v1/auth/reissue")
    suspend fun postRefresh(
        @Body refreshRequest: RefreshRequest
    ) : Response<RefreshResponse>
}