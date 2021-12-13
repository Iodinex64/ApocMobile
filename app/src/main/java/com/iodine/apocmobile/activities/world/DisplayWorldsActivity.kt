package com.iodine.apocmobile.activities.world

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iodine.apocmobile.R
import com.iodine.apocmobile.adapters.WorldAdapter
import com.iodine.apocmobile.utils.DataManager

class DisplayWorldsActivity : AppCompatActivity() {
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_worlds)

        val worldRecycler = findViewById<RecyclerView>(R.id.worldRecyclerView)

        var worldAdapter = WorldAdapter(DataManager.masterWorlds)
        worldRecycler.adapter = worldAdapter
        worldRecycler.layoutManager = LinearLayoutManager(this)
        worldAdapter.notifyDataSetChanged()

        //go to edit world activity with selected world
        worldAdapter.itemClickListener = { position, name ->
//            Toast.makeText(
//                this,
//                "position: $position - name: $name",
//                Toast.LENGTH_SHORT
//            ).show()
            DataManager.selectedItemIndex = position
            val editWorldIntent = Intent(this, EditWorldActivity::class.java)
            startActivity(editWorldIntent)
        }

        val cancelButton = findViewById<Button>(R.id.displayWorldsBackButton)
        cancelButton.setOnClickListener {
            val worldIntent = Intent(this, WorldActivity::class.java)
            startActivity(worldIntent)
        }
    }
}