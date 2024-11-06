package org.sopt.and.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class TokenManger @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        private val Context.infoDataStore: DataStore<Preferences> by preferencesDataStore(name = "info")
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        val EMAIL = stringPreferencesKey("user_email")
        val PWD = stringPreferencesKey("user_pwd")
    }

    fun saveToken(token: String) = runBlocking {
        context.infoDataStore.edit { prefs ->
            prefs[ACCESS_TOKEN_KEY] = token
        }
    }

    fun getAccessToken(): Flow<String?> {
        return  context.infoDataStore.data.map { prefs ->
            prefs[ACCESS_TOKEN_KEY]
        }
    }

    suspend fun saveEmail(email: String) {
        context.infoDataStore.edit {
            it[EMAIL] = email
        }
    }

    suspend fun savePwd(pwd: String) {
        context.infoDataStore.edit {
            it[PWD] = pwd
        }
    }

    fun getEmail(): Flow<String?> {
        return context.infoDataStore.data.map { prefs ->
            prefs[EMAIL]
        }
    }

    fun getPwd(): Flow<String?> {
        return context.infoDataStore.data.map { prefs ->
            prefs[PWD]
        }
    }

}