package com.iodine.apocmobile.activities.world

import android.content.Intent
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

        val thisWorld = DataManager.masterWorlds[DataManager.selectedItemIndex]
        val worldNameText = findViewById<TextView>(R.id.editWorldNameText)
        worldNameText.text = thisWorld.name

        val submitEditButton = findViewById<Button>(R.id.editWorldSubmitButton)
        submitEditButton.setOnClickListener {
            if (worldNameText.text.toString().isNotEmpty()) {
                DataManager.editWorld(worldNameText.text.toString(), DataManager.selectedItemIndex)
                DataManager.saveToJSON(applicationContext)
            }
            Helper.hideSoftKeyboard(this)
            startActivity(Intent(this, DisplayWorldsActivity::class.java))
        }

        val submitDeleteButton = findViewById<Button>(R.id.editWorldDeleteButton)
        submitDeleteButton.setOnClickListener {
            DataManager.removeWorldAtIndex(DataManager.selectedItemIndex)
            Helper.hideSoftKeyboard(this)
            startActivity(Intent(this, DisplayWorldsActivity::class.java))
        }

        val cancelButton = findViewById<Button>(R.id.editWorldCancelButton)
        cancelButton.setOnClickListener {
            startActivity(Intent(this, DisplayWorldsActivity::class.java))
        }
    }
}