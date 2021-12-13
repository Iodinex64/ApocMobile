package com.iodine.apocmobile.activities.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import com.iodine.apocmobile.R
import com.iodine.apocmobile.activities.world.WorldActivity
import com.iodine.apocmobile.main.MainApp
import com.iodine.apocmobile.utils.DataManager
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.i("Went to main menu!")

        val worldButton = findViewById<Button>(R.id.goToWorldsActivityButton)
        worldButton.setOnClickListener {
            val worldIntent = Intent(this, WorldActivity::class.java)
            startActivity(worldIntent)
        }

        val nukeButton = findViewById<Button>(R.id.deleteEverythingButton)
        nukeButton.setOnClickListener {
            DataManager.NukeAll(applicationContext)
            val notification = Snackbar.make(
                findViewById(android.R.id.content),
                "ALL DATA NUKED!",
                Snackbar.LENGTH_SHORT
            )
            notification.show()
            Timber.i("Uh oh, everything gone :)")
        }
    }
}