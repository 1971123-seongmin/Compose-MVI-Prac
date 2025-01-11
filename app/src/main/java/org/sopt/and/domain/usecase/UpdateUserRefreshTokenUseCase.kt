package org.sopt.and.domain.usecase

import org.sopt.and.domain.model.AuthToken
import org.sopt.and.domain.repository.AuthTokenRepository
import javax.inject.Inject

class UpdateUserRefreshTokenUseCase @Inject constructor(
    private val authTokenRepository: AuthTokenRepository,
) {
    suspend operator fun invoke(refreshBody: AuthToken) {
        authTokenRepository.saveAccessToken(refreshBody.accessToken)
        authTokenRepository.saveRefreshToken(refreshBody.refreshToken)
    }
}