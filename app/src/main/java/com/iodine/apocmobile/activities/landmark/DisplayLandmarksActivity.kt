package com.iodine.apocmobile.activities.landmark

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iodine.apocmobile.R
import com.iodine.apocmobile.adapters.LandmarkAdapter
import com.iodine.apocmobile.utils.DataManager

class DisplayLandmarksActivity : AppCompatActivity() {
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_landmarks)

        val landmarkRecycler = findViewById<RecyclerView>(R.id.landmarkRecyclerView)

        val landmarkAdapter = LandmarkAdapter(DataManager.masterLandmarks)
        landmarkRecycler.adapter = landmarkAdapter
        landmarkRecycler.layoutManager = LinearLayoutManager(this)
        landmarkAdapter.notifyDataSetChanged()

        //go to edit landmark activity with selected landmark
        landmarkAdapter.itemClickListener = { position, _ ->
            DataManager.selectedItemIndex = position
            val editLandmarkIntent = Intent(this, EditLandmarkActivity::class.java)
            startActivity(editLandmarkIntent)
        }

        val cancelButton = findViewById<Button>(R.id.displayLandmarksBackButton)
        cancelButton.setOnClickListener {
            val landmarkIntent = Intent(this, BaseLandmarkActivity::class.java)
            startActivity(landmarkIntent)
        }
    }
}