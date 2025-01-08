package org.sopt.and.data.remote.source.google

import androidx.credentials.Credential
import org.sopt.and.domain.repository.google.GoogleSignInRepository
import javax.inject.Inject

class GoogleSignInRepositoryImpl @Inject constructor(
    private val googleSignInDataSource: GoogleSignInDataSource
): GoogleSignInRepository {
    override suspend fun signIn(): Result<Credential> =
        googleSignInDataSource.signIn()
}