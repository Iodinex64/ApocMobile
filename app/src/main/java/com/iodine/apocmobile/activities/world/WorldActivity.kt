package com.iodine.apocmobile.activities.world

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.iodine.apocmobile.R
import com.iodine.apocmobile.activities.main.MainActivity

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
        createButton.setOnClickListener {
            val createWorldIntent = Intent(this, CreateWorldActivity::class.java)
            startActivity(createWorldIntent)
        }

        val editButton = findViewById<Button>(R.id.editWorldsButton)
        editButton.setOnClickListener {
            val editWorldIntent = Intent(this, DisplayWorldsActivity::class.java)
            startActivity(editWorldIntent)
        }
    }
}