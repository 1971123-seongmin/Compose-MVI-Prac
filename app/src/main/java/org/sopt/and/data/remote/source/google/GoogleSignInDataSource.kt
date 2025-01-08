package org.sopt.and.data.remote.source.google

import androidx.credentials.Credential

interface GoogleSignInDataSource {
    suspend fun signIn(): Result<Credential>
}
