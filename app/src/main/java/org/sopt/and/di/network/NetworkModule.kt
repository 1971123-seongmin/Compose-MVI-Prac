package org.sopt.and.di.network

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.sopt.and.BuildConfig
import org.sopt.and.data.api.ReissueTokenApi
import org.sopt.and.data.interceptor.AccessTokenInterceptor
import org.sopt.and.data.remote.datasource.local.TokenLocalDataSource
import org.sopt.and.domain.usecase.DeleteUserRefreshTokenUseCase
import org.sopt.and.domain.usecase.UpdateUserRefreshTokenUseCase
import org.sopt.and.utils.TokenManager
import org.sopt.and.utils.qualifier.AuthNotRequired
import org.sopt.and.utils.qualifier.AuthRequired
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    @OptIn(ExperimentalSerializationApi::class)
    fun provideJson(): Json = Json {
        isLenient = true
        prettyPrint = true
        explicitNulls = false
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    // 기존 DataSource (과제 였던 토큰 저장용 - 구글로그인 X)
    @Provides
    @Singleton
    fun provideTokenInterceptor(tokenManager: TokenManager): AccessTokenInterceptor {
        return AccessTokenInterceptor(tokenManager)
    }

    // Retrofit으로 네트워크 요청할 때 uthorization 헤더를 자동으로 추가하는 함수
    // 사용자의 액세스 토큰을 로컬에서 가져와 요청에 포함한다.
    @Provides
    @Singleton
    fun provideAuthInterceptor(
        tokenLocalDataSource: TokenLocalDataSource,
    ): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            runBlocking {
                val accessToken = tokenLocalDataSource.getAccessToken() ?: ""
                val newRequest: Request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $accessToken")
                    .build()
                chain.proceed(newRequest)
            }
        }
    }

    // 만료된 액세스 토큰이 포함된 요청이 서버에서 거부되었을 때(HTTP 401 응답)
    // 자동으로 토큰을 재발급받는 역할을 수행
    @AuthRequired
    @Provides
    @Singleton
    fun provideRefreshInterceptor(
        @ApplicationContext context: Context,
        tokenLocalDataSource: TokenLocalDataSource,
        updateUserRefreshTokenUseCase: UpdateUserRefreshTokenUseCase,
        deleteUserRefreshTokenUseCase: DeleteUserRefreshTokenUseCase,
        @AuthRequired reissueTokenApi: ReissueTokenApi
    ): Authenticator = AuthAuthenticator(context, tokenLocalDataSource, updateUserRefreshTokenUseCase, deleteUserRefreshTokenUseCase, reissueTokenApi)

    @AuthRequired
    @Provides
    @Singleton
    fun provideAuthOKHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        accessTokenInterceptor: AccessTokenInterceptor,
        authInterceptor: Interceptor,
        @AuthRequired refreshInterceptor: Authenticator,
    ): OkHttpClient =
        OkHttpClient.Builder().apply {
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
            if(BuildConfig.DEBUG) addInterceptor(httpLoggingInterceptor)
            addInterceptor(accessTokenInterceptor)
            addInterceptor(authInterceptor)
            authenticator(refreshInterceptor)
        }.build()

    @AuthNotRequired
    @Singleton
    @Provides
    fun provideOkHttpClientAuthNotRequired(
        httpLoggingInterceptor: Interceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()

    @AuthRequired
    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideAuthRetrofit(
        @AuthRequired okHttpClient: OkHttpClient,
        json: Json,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_SERVER_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory((requireNotNull("application/json".toMediaTypeOrNull()))))
            .build()
    }


    @AuthNotRequired
    @Singleton
    @Provides
    fun provideRetrofitAuthNotRequired(
        @AuthNotRequired okHttpClient: OkHttpClient,
        json: Json,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_SERVER_URL)
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory((requireNotNull("application/json".toMediaTypeOrNull()))))
        .build()

}