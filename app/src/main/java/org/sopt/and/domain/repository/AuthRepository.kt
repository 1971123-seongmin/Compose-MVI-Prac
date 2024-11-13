package org.sopt.and.domain.repository

import org.sopt.and.domain.model.auth.LoginUserEntity
import org.sopt.and.domain.model.auth.RegisterUserEntity
import org.sopt.and.domain.model.auth.UserIdEntity
import org.sopt.and.domain.model.auth.UserTokenEntity

interface AuthRepository {
    suspend fun registerUser(userEntity: RegisterUserEntity): Result<UserIdEntity>
    suspend fun loginUser(loginUserEntity: LoginUserEntity): Result<UserTokenEntity>
}