package org.sopt.and.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import org.sopt.and.data.remote.repository.GoogleSignInRepositoryImpl
import org.sopt.and.domain.repository.TokenRepository

@Module
@InstallIn(ActivityComponent::class) // ActivityScoped로 관리, 구글로그인 전용 DI
abstract class TokenRepositoryModule {
    // GoogleSignInRepository 추가
    @Binds
    @ActivityScoped
    abstract fun bindsGoogleSignInRepository(
        googleSignInRepositoryImpl: GoogleSignInRepositoryImpl
    ): TokenRepository

}