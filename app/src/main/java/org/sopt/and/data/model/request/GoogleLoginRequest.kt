package org.sopt.and.data.model.request

import org.sopt.and.utils.SocialType

data class GoogleLoginRequest(
    //@SerializedName("socialType")
    val socialType: SocialType,
    //@SerializedName("idToken")
    val idToken: String,
)