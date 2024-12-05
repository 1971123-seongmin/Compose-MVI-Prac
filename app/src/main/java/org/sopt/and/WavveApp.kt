package org.sopt.and

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import org.sopt.and.utils.TokenManager
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class WavveApp : Application() {

    @Inject
    private lateinit var tokenManager: TokenManager

    companion object {
        lateinit var wavveApp: WavveApp
        fun getInstance() = wavveApp
    }

    override fun onCreate() {
        super.onCreate()

        wavveApp = this
        tokenManager = TokenManager(this)
        initTimber()
        setDayMode()
    }

    fun getDataStore(): TokenManager = tokenManager

    private fun initTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    private fun setDayMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

}