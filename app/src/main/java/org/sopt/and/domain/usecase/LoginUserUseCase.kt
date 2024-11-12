package org.sopt.and.domain.usecase

import org.sopt.and.domain.model.user.LoginUserEntity
import org.sopt.and.domain.model.user.UserTokenEntity
import org.sopt.and.domain.repository.AuthRepository

class LoginUserUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(loginUserEntity: LoginUserEntity): Result<UserTokenEntity> =
        authRepository.loginUser(loginUserEntity)
}