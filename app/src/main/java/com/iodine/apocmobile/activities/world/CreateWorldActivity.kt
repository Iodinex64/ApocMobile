package com.iodine.apocmobile.activities.world

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.iodine.apocmobile.R
import com.iodine.apocmobile.utils.DataManager
import timber.log.Timber
import com.google.android.material.snackbar.Snackbar
import com.iodine.apocmobile.utils.Helper

class CreateWorldActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_world)
        Timber.i("Went to world menu!")

        val submitButton = findViewById<Button>(R.id.createWorldSubmitButton)
        submitButton.setOnClickListener {
            val worldNameText = findViewById<TextView>(R.id.createWorldNameText).text.toString()
            var worldExists = false
            for (world in DataManager.masterWorlds) {
                if (world.name == worldNameText) {
                    worldExists = true
                    break
                }
            }
            if (worldNameText.isNotEmpty() && !worldExists) {
                DataManager.createWorld(worldNameText)
                DataManager.saveToJSON(applicationContext)
                //show snackbar notification
                val notification = Snackbar.make(
                    findViewById(android.R.id.content),
                    "World $worldNameText successfully created.",
                    Snackbar.LENGTH_SHORT
                )
                notification.show()
            }
            else {
                val notification = Snackbar.make(
                    findViewById(android.R.id.content),
                    "World was not created.",
                    Snackbar.LENGTH_SHORT
                )
                notification.show()
            }
            Helper.hideSoftKeyboard(this)
        }

        val cancelButton = findViewById<Button>(R.id.createWorldCancelButton)
        cancelButton.setOnClickListener {
            val worldIntent = Intent(this, WorldActivity::class.java)
            startActivity(worldIntent)
        }
    }
}