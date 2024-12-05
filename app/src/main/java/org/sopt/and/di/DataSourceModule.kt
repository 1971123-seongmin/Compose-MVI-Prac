package org.sopt.and.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.remote.source.auth.AuthDataSource
import org.sopt.and.data.remote.source.auth.AuthDataSourceImpl
import org.sopt.and.data.remote.source.user.UserDataSource
import org.sopt.and.data.remote.source.user.UserDataSourceImpl
import org.sopt.and.data.service.AuthApi
import org.sopt.and.data.service.UserApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideAuthDataSource(authApi: AuthApi): AuthDataSource =
        AuthDataSourceImpl(authApi)

    @Provides
    @Singleton
    fun provideUserDataSource(userApi: UserApi): UserDataSource =
        UserDataSourceImpl(userApi)
}