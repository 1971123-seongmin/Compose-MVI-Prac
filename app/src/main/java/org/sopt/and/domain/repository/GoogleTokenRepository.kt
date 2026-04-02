package org.sopt.and.domain.repository

import androidx.credentials.Credential

interface GoogleTokenRepository {
    suspend fun signIn(): Result<Credential>
}