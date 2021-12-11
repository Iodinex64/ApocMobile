package com.iodine.apocmobile.main

import android.app.Application
import timber.log.Timber

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Timber.d("App started")
    }
}