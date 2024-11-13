package org.sopt.and.domain.usecase.user

import org.sopt.and.domain.model.user.MyHobbyEntity
import org.sopt.and.domain.repository.UserRepository

class GetMyHobbyUseCase(
    private val userRepository: UserRepository
) {
    suspend fun invoke(): Result<MyHobbyEntity> = userRepository.getMyHobby()
}