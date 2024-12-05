package org.sopt.and

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import org.sopt.and.utils.TokenManager
import timber.log.Timber

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
        initTimber()
        setDayMode()
    }

    fun getDataStore(): TokenManager = dataStore

    private fun initTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    private fun setDayMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

}