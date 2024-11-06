package org.sopt.and.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.remote.source.UserDataSource
import org.sopt.and.data.remote.source.UserDataSourceImpl
import org.sopt.and.data.service.UserApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideUserDataSource(userApi: UserApi): UserDataSource =
        UserDataSourceImpl(userApi)
}