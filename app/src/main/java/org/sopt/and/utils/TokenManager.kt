package org.sopt.and.utils

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class TokenManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        private val Context.infoDataStore: DataStore<Preferences> by preferencesDataStore(name = "info")
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
    }

    fun saveToken(token: String) = runBlocking {
        context.infoDataStore.edit { prefs ->
            prefs[ACCESS_TOKEN_KEY] = token
        }
        Log.d("토큰", "토큰 : $token")
    }

    fun getAccessToken(): Flow<String?> {
        return  context.infoDataStore.data.map { prefs ->
            prefs[ACCESS_TOKEN_KEY]
        }
    }
}