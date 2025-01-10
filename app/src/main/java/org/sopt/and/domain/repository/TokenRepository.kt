package org.sopt.and.domain.repository

import androidx.credentials.Credential

interface TokenRepository {
    suspend fun signIn(): Result<Credential>
}