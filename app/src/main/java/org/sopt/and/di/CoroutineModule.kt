package org.sopt.and.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {
    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class IoDispatcher

    @IoDispatcher // -> Qualifier
    @Provides
    fun providesIoDispatcher() = Dispatchers.IO
}