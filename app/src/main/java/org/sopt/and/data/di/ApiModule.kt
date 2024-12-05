package org.sopt.and.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.service.AuthApi
import org.sopt.and.data.service.UserApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideAuthApi(
        @NetworkModule.MainServer retrofit: Retrofit
    ) : AuthApi = retrofit.create(AuthApi::class.java)

    @Provides
    @Singleton
    fun provideUserApi(
        @NetworkModule.MainServer retrofit: Retrofit
    ) : UserApi = retrofit.create(UserApi::class.java)
}