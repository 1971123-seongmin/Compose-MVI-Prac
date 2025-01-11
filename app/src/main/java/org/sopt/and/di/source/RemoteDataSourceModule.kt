package org.sopt.and.di.source

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.api.AuthApi
import org.sopt.and.data.api.UserApi
import org.sopt.and.data.remote.datasource.remote.AuthDataSource
import org.sopt.and.data.remote.datasource.remote.UserDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {
    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(
        authApi: AuthApi
    ): AuthDataSource  {
        return AuthDataSource(authApi)
    }

    @Provides
    @Singleton
    fun provideUserDataSource(
        userApi: UserApi
    ): UserDataSource {
        return UserDataSource(userApi)
    }
}