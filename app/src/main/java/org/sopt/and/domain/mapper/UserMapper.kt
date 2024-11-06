package org.sopt.and.domain.mapper

import org.sopt.and.data.model.request.UserRegisterRequestDto
import org.sopt.and.data.model.response.UserRegisterResponse
import org.sopt.and.domain.model.user.RegisterUserEntity
import org.sopt.and.domain.model.user.UserIdEntity

object UserMapper {
    fun mapperToUserRegisterRequestDto(entity: RegisterUserEntity) = UserRegisterRequestDto(
        username = entity.username,
        password = entity.password,
        hobby = entity.hobby
    )


    fun mapperToUserIdEntity(res: UserRegisterResponse) = UserIdEntity(
            no = res.userId
    )

}