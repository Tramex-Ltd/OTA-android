package com.tramexmeters.mex5update.application

import android.app.Application
import com.tramexmeters.mex5update.bluetooth.parsing.Engine
import com.tramexmeters.mex5update.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree

@HiltAndroidApp
class SiliconLabsDemoApplication : Application() {
    companion object {
        lateinit var APP: SiliconLabsDemoApplication
    }

    init {
        APP = this
    }

    override fun onCreate() {
        super.onCreate()
        Engine.init(this)


        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}