package com.iodine.apocmobile.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.iodine.apocmobile.R

class WorldActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_world)

        val backButton = findViewById<Button>(R.id.worldsBackButton)
        backButton.setOnClickListener {
            val backIntent = Intent(this, MainActivity::class.java)
            startActivity(backIntent)
        }

        val createButton = findViewById<Button>(R.id.createWorldButton)
        backButton.setOnClickListener {
            val createWorldIntent = Intent(this, MainActivity::class.java)
            startActivity(createWorldIntent)
        }
    }
}