package org.sopt.and.di.source

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import org.sopt.and.data.remote.datasource.local.TokenLocalDataSource
import org.sopt.and.di.CoroutineModule
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {
    @Provides
    @Singleton
    fun provideTokenLocalDataSource(
        @ApplicationContext applicationContext: Context,
        @CoroutineModule.IoDispatcher dispatcherIO: CoroutineDispatcher
    ): TokenLocalDataSource {
        return TokenLocalDataSource(applicationContext, dispatcherIO)
    }
}