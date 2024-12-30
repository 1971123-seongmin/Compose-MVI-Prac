package org.sopt.and.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.domain.repository.AuthRepository
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.domain.repository.local.LocalHomeImageRepository
import org.sopt.and.domain.usecase.auth.LoginUserUseCase
import org.sopt.and.domain.usecase.auth.RegisterUserUseCase
import org.sopt.and.domain.usecase.local.GetLocalHomeImageUseCase
import org.sopt.and.domain.usecase.user.GetMyHobbyUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideRegisterUseCase(authRepository: AuthRepository): RegisterUserUseCase =
        RegisterUserUseCase(authRepository)

    @Provides
    @Singleton
    fun provideLoginUseCase(authRepository: AuthRepository): LoginUserUseCase =
        LoginUserUseCase(authRepository)

    @Provides
    @Singleton
    fun provideGetMyHobbyUseCase(userRepository: UserRepository): GetMyHobbyUseCase =
        GetMyHobbyUseCase(userRepository)

    @Provides
    @Singleton
    fun provideGetLocalHomeImageUseCase(localHomeImageRepository: LocalHomeImageRepository):
            GetLocalHomeImageUseCase = GetLocalHomeImageUseCase(localHomeImageRepository)

}