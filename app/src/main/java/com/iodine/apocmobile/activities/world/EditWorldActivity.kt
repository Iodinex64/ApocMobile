package com.iodine.apocmobile.activities.world

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iodine.apocmobile.R
import com.iodine.apocmobile.adapters.WorldAdapter
import com.iodine.apocmobile.utils.DataManager

class EditWorldActivity : AppCompatActivity() {
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_world)

        val worldRecycler = findViewById<RecyclerView>(R.id.worldRecyclerView)

        var worldAdapter = WorldAdapter(DataManager.masterWorlds)
        worldRecycler.adapter = worldAdapter
        worldRecycler.layoutManager = LinearLayoutManager(this)
        worldAdapter.notifyDataSetChanged()
    }
}