package org.sopt.and.domain.repository

import org.sopt.and.domain.model.user.RegisterUserEntity
import org.sopt.and.domain.model.user.UserIdEntity

interface AuthRepository {
    suspend fun registerUser(userEntity: RegisterUserEntity): Result<UserIdEntity>
}