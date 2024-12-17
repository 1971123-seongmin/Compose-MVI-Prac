package org.sopt.and.data.remote.source.auth

import org.sopt.and.data.mapper.AuthMapper
import org.sopt.and.domain.model.auth.LoginUserEntity
import org.sopt.and.domain.model.auth.RegisterUserEntity
import org.sopt.and.domain.model.auth.UserIdEntity
import org.sopt.and.domain.model.auth.UserTokenEntity
import org.sopt.and.domain.repository.AuthRepository
import org.sopt.and.utils.TokenManager
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val tokenManager: TokenManager
) : AuthRepository {

    override suspend fun registerUser(
        userEntity: RegisterUserEntity
    ): Result<UserIdEntity> = runCatching {
        val requestDto = AuthMapper.mapperToUserRegisterRequestDto(userEntity)
        val response = authDataSource.registerUser(requestDto)
        AuthMapper.mapperToUserIdEntity(response.result)
    }

    override suspend fun loginUser(loginUserEntity: LoginUserEntity): Result<UserTokenEntity> =
        runCatching {
            val requestDto = AuthMapper.mapperToUserLoginRequestDto(loginUserEntity)
            val response = authDataSource.loginUser(requestDto)
            val tokenEntity = AuthMapper.mapperToTUserTokenEntity(response.result)

            tokenManager.saveToken(tokenEntity.token)
            tokenEntity
        }

}