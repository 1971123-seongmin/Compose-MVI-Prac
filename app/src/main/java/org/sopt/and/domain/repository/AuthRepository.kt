package org.sopt.and.domain.repository

import org.sopt.and.domain.model.user.LoginUserEntity
import org.sopt.and.domain.model.user.RegisterUserEntity
import org.sopt.and.domain.model.user.UserIdEntity
import org.sopt.and.domain.model.user.UserTokenEntity

interface AuthRepository {
    suspend fun registerUser(userEntity: RegisterUserEntity): Result<UserIdEntity>
    suspend fun loginUser(loginUserEntity: LoginUserEntity): Result<UserTokenEntity>
}