package org.sopt.and.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import org.sopt.and.data.remote.source.google.GoogleSignInDataSource
import org.sopt.and.data.remote.source.google.GoogleSignInDataSourceImpl

@Module
@InstallIn(ActivityComponent::class) // ActivityScoped로 관리, 구글로그인 전용 DI
abstract class GoogleSignInDataSourceModule {
    @Binds
    @ActivityScoped
    abstract fun bindGoogleSignInDataSource(
        googleSignInDataSourceImpl: GoogleSignInDataSourceImpl
    ): GoogleSignInDataSource

}