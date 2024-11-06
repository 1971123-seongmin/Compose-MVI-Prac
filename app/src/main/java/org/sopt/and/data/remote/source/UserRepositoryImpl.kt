package org.sopt.and.data.remote.source

import org.sopt.and.domain.mapper.UserMapper
import org.sopt.and.domain.model.user.RegisterUserEntity
import org.sopt.and.domain.model.user.UserIdEntity
import org.sopt.and.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {

    override suspend fun registerUser(
        userEntity: RegisterUserEntity): Result<UserIdEntity> = runCatching {
            val requestDto = UserMapper.mapperToUserRegisterRequestDto(userEntity)
            val response = userDataSource.registerUser(requestDto)
            UserMapper.mapperToUserIdEntity(response.result)
        }

}