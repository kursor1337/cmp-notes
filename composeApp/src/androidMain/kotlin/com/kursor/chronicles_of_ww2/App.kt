package com.kursor.chronicles_of_ww2

import android.app.Application
import com.kursor.chronicles_of_ww2.core.configuration.Backend
import com.kursor.chronicles_of_ww2.core.configuration.BuildType
import com.kursor.chronicles_of_ww2.core.configuration.Configuration
import com.kursor.chronicles_of_ww2.core.configuration.Platform

class App : Application(), SharedApplicationProvider {

    override lateinit var sharedApplication: SharedApplication
        private set

    override fun onCreate() {
        super.onCreate()
        sharedApplication = SharedApplication(getConfiguration())
        sharedApplication.launchAndroidDebugTools()
    }

    @Suppress("SENSELESS_COMPARISON")
    private fun getConfiguration() = Configuration(
        platform = Platform(this, RealAndroidDebugTools(applicationContext)),
        buildType = if (BuildConfig.DEBUG) BuildType.Debug else BuildType.Release,
        backend = if (BuildConfig.FLAVOR == "dev") Backend.Development else Backend.Production
    )
}