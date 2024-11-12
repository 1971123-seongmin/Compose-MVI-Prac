package org.sopt.and.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.remote.source.AuthDataSource
import org.sopt.and.data.remote.source.AuthRepositoryImpl
import org.sopt.and.domain.repository.AuthRepository
import org.sopt.and.utils.TokenManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        authDataSource: AuthDataSource,
        tokenManager: TokenManager
    ): AuthRepository =
        AuthRepositoryImpl(authDataSource, tokenManager)

    @Provides
    @Singleton
    fun provideTokenManager(@ApplicationContext context: Context): TokenManager {
        return TokenManager(context)
    }

}