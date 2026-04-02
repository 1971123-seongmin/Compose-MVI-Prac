package org.sopt.and.domain.repository

interface AuthTokenRepository {
    suspend fun saveAccessToken(token: String)
    suspend fun saveRefreshToken(token: String)
    suspend fun getAccessToken(): String?
    suspend fun getRefreshToken(): String?
    suspend fun removeAccessToken()
    suspend fun removeRefreshToken()
}