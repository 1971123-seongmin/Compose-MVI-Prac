package org.sopt.and.di.source

import android.content.Context
import androidx.credentials.CredentialManager
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import org.sopt.and.BuildConfig
import org.sopt.and.data.remote.datasource.remote.TokenRemoteDataSource

@Module
@InstallIn(ActivityComponent::class)
object RemoteGoogleDataSourceModule {
    @Provides
    @ActivityScoped
    fun provideGoogleIdOptions(): GetGoogleIdOption {
        return GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setAutoSelectEnabled(false)
            .setServerClientId(BuildConfig.GOOGLE_CLIENT_ID)
            .build()
    }

    @Provides
    @ActivityScoped
    fun provideCredentialManager(@ActivityContext context: Context): CredentialManager {
        return CredentialManager.create(context)
    }

    @Provides
    @ActivityScoped
    fun provideTokenRemoteDataSource(
        credentialManager: CredentialManager,
        googleIdOption: GetGoogleIdOption,
        @ActivityContext context: Context
    ): TokenRemoteDataSource {
        return TokenRemoteDataSource(credentialManager, googleIdOption, context)
    }
}
