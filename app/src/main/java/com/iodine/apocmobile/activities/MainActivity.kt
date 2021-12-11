package com.iodine.apocmobile.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.iodine.apocmobile.R
import com.iodine.apocmobile.main.MainApp
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
    }
}