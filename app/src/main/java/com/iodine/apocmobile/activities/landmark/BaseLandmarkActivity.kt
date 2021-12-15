package com.iodine.apocmobile.activities.landmark

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.iodine.apocmobile.R
//import com.iodine.apocmobile.activities.landmark.CreateLandmarkActivity
//import com.iodine.apocmobile.activities.landmark.DisplayLandmarksActivity
import com.iodine.apocmobile.activities.main.MainActivity

class BaseLandmarkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_landmark)

        val backButton = findViewById<Button>(R.id.landmarksBackButton)
        backButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val createButton = findViewById<Button>(R.id.createlandmarkButton)
        createButton.setOnClickListener {
            startActivity(Intent(this, CreateLandmarkActivity::class.java))
        }

        val editButton = findViewById<Button>(R.id.editlandmarksButton)
        editButton.setOnClickListener {
            startActivity(Intent(this, DisplayLandmarksActivity::class.java))
        }
    }
}