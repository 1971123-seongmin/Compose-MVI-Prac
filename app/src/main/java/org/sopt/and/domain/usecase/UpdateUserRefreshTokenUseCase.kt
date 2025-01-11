package org.sopt.and.domain.usecase

import org.sopt.and.data.model.response.GoogleLoginResponse
import org.sopt.and.domain.repository.AuthTokenRepository
import javax.inject.Inject

class UpdateUserRefreshTokenUseCase @Inject constructor(
    private val authTokenRepository: AuthTokenRepository,
) {
    suspend operator fun invoke(refreshBody: GoogleLoginResponse) {
        authTokenRepository.saveAccessToken(refreshBody.accessToken)
        authTokenRepository.saveRefreshToken(refreshBody.refreshToken)
    }
}