package org.sopt.and.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.remote.source.auth.AuthDataSource
import org.sopt.and.data.remote.source.auth.AuthRepositoryImpl
import org.sopt.and.data.remote.source.user.UserDataSource
import org.sopt.and.data.remote.source.user.UserRepositoryImpl
import org.sopt.and.domain.repository.AuthRepository
import org.sopt.and.domain.repository.UserRepository
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
    fun provideUserRepository(
        userDataSource: UserDataSource
    ): UserRepository =
        UserRepositoryImpl(userDataSource)


}