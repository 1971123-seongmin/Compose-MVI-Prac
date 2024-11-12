package org.sopt.and.domain.usecase

import org.sopt.and.domain.model.user.RegisterUserEntity
import org.sopt.and.domain.model.user.UserIdEntity
import org.sopt.and.domain.repository.AuthRepository

class RegisterUserUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(registerUserEntity: RegisterUserEntity): Result<UserIdEntity> =
        authRepository.registerUser(registerUserEntity)
}