package org.sopt.and.di.network

import android.content.Context
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import org.sopt.and.data.api.AuthApi
import org.sopt.and.data.model.request.RefreshRequest
import org.sopt.and.data.remote.datasource.local.TokenLocalDataSource
import org.sopt.and.domain.usecase.DeleteUserRefreshTokenUseCase
import org.sopt.and.domain.usecase.UpdateUserRefreshTokenUseCase
import timber.log.Timber
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val context: Context,
    private val tokenLocalDataSource: TokenLocalDataSource,
    private val updateUserRefreshTokenUseCase: UpdateUserRefreshTokenUseCase,
    private val deleteUserRefreshTokenUseCase: DeleteUserRefreshTokenUseCase,
    private val authApi: AuthApi,
    private val maxRetry: Int = 5,
) : Authenticator {
    private val mutex = Mutex()

    // OkHttp에서 HTTP 요청이 401 상태 코드를 반환하면 호출됨
    // 액세스 토큰이 만료되면 자동으로 액세스 토큰을 재발급 요청 하는 함수
    override fun authenticate(route: Route?, response: Response): Request? = runBlocking {
        mutex.withLock {
            Timber.e("HTTP 401 response : $response")
            Timber.e("토큰 재발급 요청 시도")
            // 지정 최대 시도 횟수를 초과하면 로그인 화면으로 이동
            if (response.responseCount() > maxRetry) {
                deleteUserRefreshTokenUseCase() // RefreshToken 삭제
                goToLoginActivity()
                return@withLock null
            }

            // 현재 리프레시 토큰 가져오기
            val currentRefreshToken = tokenLocalDataSource.getRefreshToken() ?: ""

            // 토큰 재발급 API 호출
            val newResponse = runCatching {
                authApi.postRefresh(RefreshRequest(currentRefreshToken))
            }.onSuccess {
                if (!it.isSuccessful) {
                    Timber.e("Refresh API HTTP Exception : $it")
                    deleteUserRefreshTokenUseCase() // RefreshToken 삭제
                    goToLoginActivity() // 로그인 화면으로 이동
                    return@withLock null
                }
            }.onFailure {
                Timber.e("Refresh 재발급 API 호출 에러 : ${it.message}")
            }.getOrNull()

            // 재발급된 토큰 추출 (실패시 삭제)
            val tokenBody = newResponse?.body()?.refreshResponseToGoogleLogin() ?: run {
                deleteUserRefreshTokenUseCase() // RefreshToken 삭제
                goToLoginActivity()
                return@withLock null
            }

            // 재발급된 토큰 저장 및 새 요청 생성
            updateUserRefreshTokenUseCase(tokenBody)
            response.request.newBuilder()
                .removeHeader("Authorization")
                .addHeader("Authorization", "Bearer ${tokenBody.accessToken}")
                .build()
        }
    }

    // 무한 재시도 방지를 위해 재시도 요청 횟수 계산
    private fun Response.responseCount(): Int {
        var response: Response? = this
        var result = 1
        while (response?.priorResponse.also { response = it } != null) {
            result++
        }
        return result
    }

    private fun goToLoginActivity() {
//        val handler = HandlerCompat.createAsync(Looper.getMainLooper())
//        Intent(context.applicationContext, LoginActivity::class.java).run {
//            handler.post { context.applicationContext.showToast(context.getString(R.string.token_out_dated)) }
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            context.startActivity(this)
//        }
    }
}