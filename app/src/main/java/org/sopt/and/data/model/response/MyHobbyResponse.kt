package org.sopt.and.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.and.domain.model.user.MyHobbyEntity

@Serializable
data class MyHobbyResponse(
    @SerialName("hobby")
    val hobby: String
) {
    fun mapperToMyHobbyEntity() = MyHobbyEntity(hobby)
}
