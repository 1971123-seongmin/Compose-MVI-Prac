package org.sopt.and

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.sopt.and.utils.TokenManager

@HiltAndroidApp
class GlobalApplication: Application() {
    private lateinit var dataStore: TokenManager

    companion object {
        lateinit var globalApplication: GlobalApplication
        fun getInstance() = globalApplication
    }

    override fun onCreate() {
        super.onCreate()

        globalApplication = this
        dataStore = TokenManager(this)
    }

    fun getDataStore(): TokenManager = dataStore

}