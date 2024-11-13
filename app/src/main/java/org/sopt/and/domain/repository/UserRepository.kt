package org.sopt.and.domain.repository

import org.sopt.and.domain.model.user.MyHobbyEntity

interface UserRepository {
    suspend fun getMyHobby() : Result<MyHobbyEntity>
}