package org.sopt.and.domain.usecase.auth

import org.sopt.and.domain.model.auth.RegisterUserEntity
import org.sopt.and.domain.model.auth.UserIdEntity
import org.sopt.and.domain.repository.AuthRepository

class RegisterUserUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(registerUserEntity: RegisterUserEntity): Result<UserIdEntity> =
        authRepository.registerUser(registerUserEntity)
}