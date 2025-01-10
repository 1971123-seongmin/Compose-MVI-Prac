package org.sopt.and.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.remote.repository.LocalHomeImageRepositoryImpl
import org.sopt.and.data.remote.repository.AuthRepositoryImpl
import org.sopt.and.data.remote.repository.UserRepositoryImpl
import org.sopt.and.domain.repository.AuthRepository
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.domain.repository.LocalHomeImageRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindsUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    @Singleton
    abstract fun bindsLocalHomeImageRepository(
        localHomeImageRepositoryImpl: LocalHomeImageRepositoryImpl
    ): LocalHomeImageRepository

}