package org.sopt.and.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.remote.source.auth.AuthDataSource
import org.sopt.and.data.remote.source.auth.AuthDataSourceImpl
import org.sopt.and.data.service.AuthApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideAuthDataSource(authApi: AuthApi): AuthDataSource =
        AuthDataSourceImpl(authApi)
}