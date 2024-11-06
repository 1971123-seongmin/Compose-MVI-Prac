package org.sopt.and.domain.usecase

import org.sopt.and.domain.model.user.RegisterUserEntity
import org.sopt.and.domain.model.user.UserIdEntity
import org.sopt.and.domain.repository.UserRepository

class RegisterUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(registerUserEntity: RegisterUserEntity): Result<UserIdEntity> =
        userRepository.registerUser(registerUserEntity)
}