package org.sopt.and.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.remote.source.UserDataSource
import org.sopt.and.data.remote.source.UserRepositoryImpl
import org.sopt.and.domain.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(userDataSource: UserDataSource): UserRepository =
        UserRepositoryImpl(userDataSource)

}