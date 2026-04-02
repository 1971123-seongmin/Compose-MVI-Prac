package org.sopt.and.domain.repository

import org.sopt.and.domain.model.AuthToken
import org.sopt.and.domain.model.auth.LoginUserEntity
import org.sopt.and.domain.model.auth.RegisterUserEntity
import org.sopt.and.domain.model.auth.UserIdEntity
import org.sopt.and.domain.model.auth.UserTokenEntity
import org.sopt.and.utils.SocialType

interface AuthRepository {
    suspend fun postLogin(socialType: SocialType, idToken: String): Result<Unit> // 구글 로그인

    suspend fun registerUser(userEntity: RegisterUserEntity): Result<UserIdEntity>
    suspend fun loginUser(loginUserEntity: LoginUserEntity): Result<UserTokenEntity>

}