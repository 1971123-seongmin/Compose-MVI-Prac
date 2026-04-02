package org.sopt.and.data.remote.repository

import kotlinx.coroutines.runBlocking
import org.sopt.and.data.remote.datasource.local.TokenLocalDataSource
import org.sopt.and.domain.repository.AuthTokenRepository
import javax.inject.Inject

class AuthTokenRepositoryImpl @Inject constructor(
    private val tokenLocalDataSource: TokenLocalDataSource,
): AuthTokenRepository {
    override suspend fun saveAccessToken(token: String) {
        tokenLocalDataSource.saveAccessToken(token)
    }

    override suspend fun saveRefreshToken(token: String) {
        tokenLocalDataSource.saveRefreshToken(token)
    }

    override suspend fun getAccessToken(): String? {
        return runBlocking { tokenLocalDataSource.getAccessToken() }
    }

    override suspend fun getRefreshToken(): String? {
        return runBlocking { tokenLocalDataSource.getRefreshToken() }
    }

    override suspend fun removeAccessToken() {
        tokenLocalDataSource.removeAccessToken()
    }

    override suspend fun removeRefreshToken() {
        tokenLocalDataSource.removeRefreshToken()
    }
}