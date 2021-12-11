package com.iodine.apocmobile.main
import android.app.Application
import com.iodine.apocmobile.utils.DataManager
import timber.log.Timber

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        DataManager.readFromJSON(c = applicationContext)
        Timber.plant(Timber.DebugTree())
        Timber.i("Apocrypha app started! :)")

    }
}