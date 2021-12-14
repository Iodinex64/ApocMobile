package com.iodine.apocmobile.activities.location

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.iodine.apocmobile.R
import com.iodine.apocmobile.activities.main.MainActivity
import com.iodine.apocmobile.activities.world.CreateWorldActivity
import com.iodine.apocmobile.activities.world.DisplayWorldsActivity

class BaseLocationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_location)

        val backButton = findViewById<Button>(R.id.locationsBackButton)
        backButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val createButton = findViewById<Button>(R.id.createlocationButton)
        createButton.setOnClickListener {
            startActivity(Intent(this, CreateLocationActivity::class.java))
        }

        val editButton = findViewById<Button>(R.id.editlocationsButton)
        editButton.setOnClickListener {
            startActivity(Intent(this, DisplayLocationsActivity::class.java))
        }
    }
}