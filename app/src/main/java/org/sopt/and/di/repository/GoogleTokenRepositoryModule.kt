package org.sopt.and.di.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import org.sopt.and.data.remote.repository.GoogleTokenRepositoryImpl
import org.sopt.and.domain.repository.GoogleTokenRepository

@Module
@InstallIn(ActivityComponent::class) // ActivityScoped로 관리, 구글로그인 전용 DI
abstract class GoogleTokenRepositoryModule {
    // GoogleSignInRepository 추가
    @Binds
    @ActivityScoped
    abstract fun bindsGoogleSignInRepository(
        googleSignInRepositoryImpl: GoogleTokenRepositoryImpl
    ): GoogleTokenRepository

}