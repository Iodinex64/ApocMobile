package com.iodine.apocmobile.activities.location

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import com.iodine.apocmobile.R
import com.iodine.apocmobile.activities.world.DisplayWorldsActivity
import com.iodine.apocmobile.models.Location
import com.iodine.apocmobile.utils.DataManager
import com.iodine.apocmobile.utils.Helper
import timber.log.Timber

class EditLocationActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_location)


        val thisLocation = DataManager.masterLocations[DataManager.selectedItemIndex]

        val locationNameText = findViewById<TextView>(R.id.editLocationNameText)
        val locationBioText = findViewById<TextView>(R.id.editLocationBioText)
        val locationWorldText = findViewById<TextView>(R.id.editLocationWorldName)

        //edit related assignments
        locationNameText.text = thisLocation.name
        locationBioText.text = thisLocation.bio
        locationWorldText.text = "This location belongs to " + thisLocation.worldName

        val submitButton = findViewById<Button>(R.id.editLocationSubmitButton)
        submitButton.setOnClickListener {
            var locationExists = false
            for (location in DataManager.masterLocations) {
                if (location.name == locationNameText.text) {
                    locationExists = true
                    break
                }
            }
            if (locationNameText.text.isNotEmpty() && !locationExists) {
                val l = Location(locationNameText.text.toString(),
                    locationBioText.text.toString(),
                    thisLocation.worldName)

                DataManager.editLocation(l, DataManager.selectedItemIndex)
                DataManager.saveToJSON(applicationContext)
                startActivity(Intent(this, DisplayLocationsActivity::class.java))
            }
            else {
                val notification = Snackbar.make(
                    findViewById(android.R.id.content),
                    "location was not edited.",
                    Snackbar.LENGTH_SHORT
                )
                notification.show()
            }
            Helper.hideSoftKeyboard(this)
        }

        val cancelButton = findViewById<Button>(R.id.editLocationCancelButton)
        cancelButton.setOnClickListener {
            startActivity(Intent(this, BaseLocationActivity::class.java))
        }

        val submitDeleteButton = findViewById<Button>(R.id.editLocationDeleteButton)
        submitDeleteButton.setOnClickListener {
            DataManager.removeLocationAtIndex(DataManager.selectedItemIndex)
            Helper.hideSoftKeyboard(this)
            startActivity(Intent(this, DisplayLocationsActivity::class.java))
        }
    }
}