package org.sopt.and.domain.repository

import androidx.credentials.Credential

interface GoogleSignInRepository {
    suspend fun signIn(): Result<Credential>
}