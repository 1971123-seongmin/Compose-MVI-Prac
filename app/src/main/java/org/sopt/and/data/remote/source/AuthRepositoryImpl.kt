package org.sopt.and.data.remote.source

import org.sopt.and.domain.mapper.UserMapper
import org.sopt.and.domain.model.user.LoginUserEntity
import org.sopt.and.domain.model.user.RegisterUserEntity
import org.sopt.and.domain.model.user.UserIdEntity
import org.sopt.and.domain.model.user.UserTokenEntity
import org.sopt.and.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
) : AuthRepository {

    override suspend fun registerUser(
        userEntity: RegisterUserEntity
    ): Result<UserIdEntity> = runCatching {
        val requestDto = UserMapper.mapperToUserRegisterRequestDto(userEntity)
        val response = authDataSource.registerUser(requestDto)
        UserMapper.mapperToUserIdEntity(response.result)
    }

    override suspend fun loginUser(loginUserEntity: LoginUserEntity): Result<UserTokenEntity> =
        runCatching {
            val requestDto = UserMapper.mapperToUserLoginRequestDto(loginUserEntity)
            val response = authDataSource.loginUser(requestDto)
            UserMapper.mapperToTUserTokenEntity(response.result)
        }

}