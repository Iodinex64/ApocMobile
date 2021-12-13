package com.iodine.apocmobile.activities.world

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.iodine.apocmobile.R
import com.iodine.apocmobile.activities.main.MainActivity

class BaseWorldActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_world)

        val backButton = findViewById<Button>(R.id.worldsBackButton)
        backButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val createButton = findViewById<Button>(R.id.createWorldButton)
        createButton.setOnClickListener {
            startActivity(Intent(this, CreateWorldActivity::class.java))
        }

        val editButton = findViewById<Button>(R.id.editWorldsButton)
        editButton.setOnClickListener {
            startActivity(Intent(this, DisplayWorldsActivity::class.java))
        }
    }
}