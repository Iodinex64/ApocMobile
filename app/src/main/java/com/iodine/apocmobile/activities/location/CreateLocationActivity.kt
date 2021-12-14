package com.iodine.apocmobile.activities.location

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import com.iodine.apocmobile.R
import com.iodine.apocmobile.utils.DataManager
import com.iodine.apocmobile.utils.Helper
import timber.log.Timber

class CreateLocationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_location)

        val worldSpinner = findViewById<Spinner>(R.id.editLocationSelectWorldSpinner)
        val locationNameText = findViewById<TextView>(R.id.editLocationNameText).text.toString()
        val locationBioText = findViewById<TextView>(R.id.editLocationBioText).text.toString()

        val worldNames = arrayListOf<String>()
        for (world in DataManager.masterWorlds) {
            worldNames.add(world.name)
        }

        val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_dropdown_item,
            worldNames)
        worldSpinner.adapter = adapter

        worldSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                DataManager.selectedItemIndex = position
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Timber.i("nothing selected")
            }
        }

        val submitButton = findViewById<Button>(R.id.editLocationSubmitButton)
        submitButton.setOnClickListener {

            var locationExists = false
            for (location in DataManager.masterLocations) {
                if (location.name == locationNameText) {
                    locationExists = true
                    break
                }
            }
            if (locationNameText.isNotEmpty() && !locationExists) {
                DataManager.createLocation(locationNameText, locationBioText, DataManager.masterWorlds[DataManager.selectedItemIndex])
                DataManager.saveToJSON(applicationContext)
                startActivity(Intent(this, DisplayLocationsActivity::class.java))
            }
            else {
                val notification = Snackbar.make(
                    findViewById(android.R.id.content),
                    "location was not created. Did you select a world?",
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
    }
}