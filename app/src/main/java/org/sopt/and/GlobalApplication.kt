package org.sopt.and

import android.app.Application
import org.sopt.and.utils.TokenManger

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