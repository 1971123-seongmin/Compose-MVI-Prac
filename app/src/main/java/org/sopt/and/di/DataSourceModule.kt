package org.sopt.and.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.remote.local.LocalHomeImageDataSource
import org.sopt.and.data.remote.local.LocalHomeImageDataSourceImpl
import org.sopt.and.data.remote.source.auth.AuthDataSource
import org.sopt.and.data.remote.source.auth.AuthDataSourceImpl
import org.sopt.and.data.remote.source.user.UserDataSource
import org.sopt.and.data.remote.source.user.UserDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindAuthDataSource(authDataSourceImpl: AuthDataSourceImpl):
        AuthDataSource

    @Binds
    @Singleton
    abstract fun bindUserDataSource(userDataSourceImpl: UserDataSourceImpl):
        UserDataSource

    @Binds
    @Singleton
    abstract fun bindLocalHomeImageDataSource(localHomeDataSourceImpl: LocalHomeImageDataSourceImpl): LocalHomeImageDataSource

}