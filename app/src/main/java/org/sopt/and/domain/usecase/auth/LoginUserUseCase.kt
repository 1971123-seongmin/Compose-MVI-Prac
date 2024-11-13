package org.sopt.and.domain.usecase.auth

import org.sopt.and.domain.model.auth.LoginUserEntity
import org.sopt.and.domain.model.auth.UserTokenEntity
import org.sopt.and.domain.repository.AuthRepository

class LoginUserUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(loginUserEntity: LoginUserEntity): Result<UserTokenEntity> =
        authRepository.loginUser(loginUserEntity)
}