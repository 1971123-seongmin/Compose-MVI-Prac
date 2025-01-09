package org.sopt.and.di

import android.content.Context
import androidx.credentials.CredentialManager
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import org.sopt.and.BuildConfig

@Module
@InstallIn(ActivityComponent::class)
object GoogleAuthModule {
    // Google ID 옵션 제공 함수
    @Provides
    @ActivityScoped
    fun provideGoogleIdOptions(): GetGoogleIdOption {
        return GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setAutoSelectEnabled(false) // 재방문 사용자의 자동 로그인 사용 설정
            .setServerClientId(BuildConfig.GOOGLE_CLIENT_ID) // 클라이언트 ID 설정
            .build()
    }

    // Google의 최신 인증 API를 사용하는 객체
    @Provides
    @ActivityScoped
    fun provideCredentialManager(
        @ActivityContext context: Context): CredentialManager {
        return CredentialManager.create(context)
    }

}