package com.iodine.apocmobile.activities.location

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iodine.apocmobile.R
import com.iodine.apocmobile.adapters.LocationAdapter
import com.iodine.apocmobile.utils.DataManager

class DisplayLocationsActivity : AppCompatActivity() {
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_locations)

        val locationRecycler = findViewById<RecyclerView>(R.id.locationRecyclerView)

        val locationAdapter = LocationAdapter(DataManager.masterLocations)
        locationRecycler.adapter = locationAdapter
        locationRecycler.layoutManager = LinearLayoutManager(this)
        locationAdapter.notifyDataSetChanged()

        //go to edit location activity with selected location
        locationAdapter.itemClickListener = { position, _ ->
            DataManager.selectedItemIndex = position
            val editLocationIntent = Intent(this, EditLocationActivity::class.java)
            startActivity(editLocationIntent)
        }

        val cancelButton = findViewById<Button>(R.id.displayLocationsBackButton)
        cancelButton.setOnClickListener {
            val locationIntent = Intent(this, BaseLocationActivity::class.java)
            startActivity(locationIntent)
        }
    }
}