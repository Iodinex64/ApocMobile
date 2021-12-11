package com.iodine.apocmobile.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.iodine.apocmobile.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val worldButton = findViewById<Button>(R.id.goToWorldsActivityButton)
        worldButton.setOnClickListener {
            val worldIntent = Intent(this, WorldActivity::class.java)
            startActivity(worldIntent)
        }
    }
}