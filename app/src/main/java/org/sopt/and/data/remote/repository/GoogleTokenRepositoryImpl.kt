package org.sopt.and.data.remote.repository

import androidx.credentials.Credential
import org.sopt.and.data.remote.datasource.remote.TokenRemoteDataSource
import org.sopt.and.domain.repository.GoogleTokenRepository
import javax.inject.Inject

class GoogleTokenRepositoryImpl @Inject constructor(
    private val googleSignInDataSource: TokenRemoteDataSource
): GoogleTokenRepository {
    override suspend fun signIn(): Result<Credential> =
        googleSignInDataSource.signIn()
}