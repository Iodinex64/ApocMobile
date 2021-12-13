package com.iodine.apocmobile.activities.world

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.iodine.apocmobile.R
import com.iodine.apocmobile.utils.DataManager
import com.iodine.apocmobile.utils.Helper

class EditWorldActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_world)

        var thisWorld = DataManager.masterWorlds[DataManager.selectedItemIndex]
        val worldNameText = findViewById<TextView>(R.id.editWorldNameText)
        worldNameText.text = thisWorld.name
    }
}