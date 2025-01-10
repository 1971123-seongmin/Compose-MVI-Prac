package org.sopt.and.domain.repository.google

import androidx.credentials.Credential

interface GoogleSignInRepository {
    suspend fun signIn(): Result<Credential>
}