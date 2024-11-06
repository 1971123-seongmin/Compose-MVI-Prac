package org.sopt.and

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.sopt.and.utils.TokenManger

@HiltAndroidApp
class GlobalApplication: Application() {
    private lateinit var dataStore: TokenManger

    companion object {
        lateinit var globalApplication: GlobalApplication
        fun getInstance() = globalApplication
    }

    override fun onCreate() {
        super.onCreate()

        globalApplication = this
        dataStore = TokenManger(this)
    }

    fun getDataStore(): TokenManger = dataStore

}