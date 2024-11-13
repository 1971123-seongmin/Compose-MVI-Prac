package org.sopt.and.data.remote.source

import org.sopt.and.domain.mapper.AuthMapper
import org.sopt.and.domain.model.user.LoginUserEntity
import org.sopt.and.domain.model.user.RegisterUserEntity
import org.sopt.and.domain.model.user.UserIdEntity
import org.sopt.and.domain.model.user.UserTokenEntity
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