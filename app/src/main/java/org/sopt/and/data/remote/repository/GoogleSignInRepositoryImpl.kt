package org.sopt.and.data.remote.repository

import androidx.credentials.Credential
import org.sopt.and.data.remote.datasource.remote.TokenRemoteDataSource
import org.sopt.and.domain.repository.GoogleSignInRepository
import javax.inject.Inject

class GoogleSignInRepositoryImpl @Inject constructor(
    private val googleSignInDataSource: TokenRemoteDataSource
): GoogleSignInRepository {
    override suspend fun signIn(): Result<Credential> =
        googleSignInDataSource.signIn()
}