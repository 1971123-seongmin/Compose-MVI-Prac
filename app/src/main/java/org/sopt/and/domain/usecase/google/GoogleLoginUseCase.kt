package org.sopt.and.domain.usecase.google

import androidx.credentials.Credential
import org.sopt.and.domain.repository.google.GoogleSignInRepository

class GoogleLoginUseCase(
    private val googleSignInRepository: GoogleSignInRepository
) {
    suspend operator fun invoke(): Result<Credential> =
        googleSignInRepository.signIn()
}