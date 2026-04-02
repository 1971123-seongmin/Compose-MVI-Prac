package org.sopt.and.domain.usecase

import org.sopt.and.domain.repository.AuthTokenRepository
import javax.inject.Inject

class DeleteUserRefreshTokenUseCase @Inject constructor(
    private val authTokenRepository: AuthTokenRepository
) {
    suspend operator fun invoke() {
        authTokenRepository.removeAccessToken()
        authTokenRepository.removeRefreshToken()
    }
}