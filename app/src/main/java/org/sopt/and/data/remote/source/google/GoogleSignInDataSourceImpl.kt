package org.sopt.and.data.remote.source.google

import android.content.Context
import android.util.Log
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import dagger.hilt.android.qualifiers.ActivityContext
import timber.log.Timber
import javax.inject.Inject

class GoogleSignInDataSourceImpl @Inject constructor(
    private val credentialManager: CredentialManager,
    private val googleIdOption: GetGoogleIdOption,
    @ActivityContext private val context: Context
) : GoogleSignInDataSource {

    val tag = "구글로그인11"

    override suspend fun signIn(): Result<Credential> {
        return runCatching {
            // Credential 요청 생성
            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption) // 주입된 옵션 사용
                .build()

            // CredentialManager로 요청 실행
            val response = credentialManager.getCredential(
                request = request,
                context = context
            )

            // 응답에서 Credential 추출 및 타입 확인
            when (val credential = response.credential) {
                is CustomCredential -> {
                    if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                        try {
                            val googleIdCredential = GoogleIdTokenCredential
                                .createFrom(credential.data) // ID Token 데이터 추출
                            Timber.tag(tag).d("Google ID Token: ${googleIdCredential.idToken}")
                            Result.success(credential)
                        } catch (e: GoogleIdTokenParsingException) {
                            Timber.tag(tag).e(e, "Invalid Google ID Token response")
                            throw e // 예외를 다시 던져 실패 처리
                        }
                    } else {
                        Timber.tag(tag).w("Unsupported credential type: ${credential.type}")
                        throw IllegalStateException("Unsupported credential type")
                    }
                }
                else -> {
                    Timber.tag(tag).e("Unknown credential type: ${credential::class.simpleName}")
                    throw IllegalStateException("Unknown credential type")
                }
            }
        }.getOrElse { throwable ->
            // 실패 처리
            Timber.tag(tag).e(throwable, "Error during sign-in: ${throwable.localizedMessage}")
            Result.failure(throwable)
        }
    }

}
