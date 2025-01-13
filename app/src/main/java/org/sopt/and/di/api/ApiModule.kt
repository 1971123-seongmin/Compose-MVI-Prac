package org.sopt.and.di.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.api.AuthApi
import org.sopt.and.data.api.ReissueTokenApi
import org.sopt.and.data.api.UserApi
import org.sopt.and.di.network.NetworkModule
import org.sopt.and.utils.qualifier.AuthNotRequired
import org.sopt.and.utils.qualifier.AuthRequired
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideAuthApi(
        @AuthRequired retrofit: Retrofit
    ) : AuthApi = retrofit.create(AuthApi::class.java)

    @AuthRequired
    @Provides
    @Singleton
    fun provideReissueTokenApi(
        @AuthNotRequired retrofit: Retrofit
    ) : ReissueTokenApi = retrofit.create(ReissueTokenApi::class.java)

    @Provides
    @Singleton
    fun provideUserApi(
        @AuthNotRequired retrofit: Retrofit
    ) : UserApi = retrofit.create(UserApi::class.java)
}