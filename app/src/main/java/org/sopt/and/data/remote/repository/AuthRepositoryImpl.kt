package org.sopt.and.data.remote.repository

import org.sopt.and.data.mapper.AuthMapper
import org.sopt.and.data.model.request.GoogleLoginRequest
import org.sopt.and.data.model.request.RefreshRequest
import org.sopt.and.data.remote.datasource.local.TokenLocalDataSource
import org.sopt.and.data.remote.datasource.remote.AuthDataSource
import org.sopt.and.domain.model.AuthToken
import org.sopt.and.domain.model.auth.LoginUserEntity
import org.sopt.and.domain.model.auth.RegisterUserEntity
import org.sopt.and.domain.model.auth.UserIdEntity
import org.sopt.and.domain.model.auth.UserTokenEntity
import org.sopt.and.domain.repository.AuthRepository
import org.sopt.and.utils.SocialType
import org.sopt.and.utils.TokenManager
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val tokenManager: TokenManager,
    private val tokenLocalDataSource: TokenLocalDataSource // 토큰 저장소
) : AuthRepository {
    // 구글 로그인
    override suspend fun postLogin(
        socialType: SocialType,
        idToken: String
    ): AuthToken {
        val googleLoginResponse = authDataSource.postLogin(GoogleLoginRequest(socialType, idToken))
        // tokenLocalDataSource.saveAccessToken(googleLoginResponse.accessToken)
        // tokenLocalDataSource.saveAccessToken(googleLoginResponse.refreshToken)
        // 여기서 이렇게 서버에서 온 토큰을 직접 저장해야하는지 모르겠습니다..
        return AuthToken(googleLoginResponse.accessToken, googleLoginResponse.refreshToken)
    }

    override suspend fun registerUser(
        userEntity: RegisterUserEntity
    ): Result<UserIdEntity> {
        return runCatching {
            val requestDto = AuthMapper.mapperToUserRegisterRequestDto(userEntity)
            val response = authDataSource.registerUser(requestDto)
            AuthMapper.mapperToUserIdEntity(response.result)
        }
    }
    override suspend fun loginUser(loginUserEntity: LoginUserEntity): Result<UserTokenEntity> {
        return runCatching {
            val requestDto = AuthMapper.mapperToUserLoginRequestDto(loginUserEntity)
            val response = authDataSource.loginUser(requestDto)
            val tokenEntity = AuthMapper.mapperToTUserTokenEntity(response.result)

            tokenManager.saveToken(tokenEntity.token)
            tokenEntity
        }
    }

}