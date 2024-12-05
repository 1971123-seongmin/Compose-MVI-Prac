package org.sopt.and.data.remote.source.user

import org.sopt.and.domain.model.user.MyHobbyEntity
import org.sopt.and.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {
    override suspend fun getMyHobby(): Result<MyHobbyEntity> =
        runCatching {
            userDataSource.getMyHobby().result.mapperToMyHobbyEntity()
        }

}