package com.iodine.apocmobile.activities.landmark

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import com.iodine.apocmobile.R
import com.iodine.apocmobile.activities.landmark.BaseLandmarkActivity
import com.iodine.apocmobile.activities.landmark.DisplayLandmarksActivity
import com.iodine.apocmobile.utils.DataManager
import com.iodine.apocmobile.utils.Helper
import org.w3c.dom.Text
import timber.log.Timber

class CreateLandmarkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_landmark)

        val locationSpinner = findViewById<Spinner>(R.id.editLandmarkSelectLocationSpinner)

        val locationNames = arrayListOf<String>()
        for (location in DataManager.masterLocations) {
            locationNames.add(location.name)
        }

        val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_dropdown_item,
            locationNames)
        locationSpinner.adapter = adapter

        locationSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                DataManager.selectedItemIndex = position
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Timber.i("nothing selected")
            }
        }

        val submitButton = findViewById<Button>(R.id.editLandmarkSubmitButton)
        submitButton.setOnClickListener {
            val landmarkNameText = findViewById<TextView>(R.id.editLandmarkNameText).text.toString()
            val landmarkBioText = findViewById<TextView>(R.id.editLandmarkBioText).text.toString()
            val landmarkPopNum = findViewById<TextView>(R.id.editLandmarkPopulationNumber).text.toString().toInt()

            var landmarkExists = false
            for (landmark in DataManager.masterLandmarks) {
                if (landmark.name == landmarkNameText) {
                    landmarkExists = true
                    break
                }
            }
            if (landmarkNameText.isNotEmpty() && !landmarkExists) {
                DataManager.createLandmark(landmarkNameText, landmarkBioText, landmarkPopNum, DataManager.masterLocations[DataManager.selectedItemIndex])
                DataManager.saveToJSON(applicationContext)
                startActivity(Intent(this, DisplayLandmarksActivity::class.java))
            }
            else {
                val notification = Snackbar.make(
                    findViewById(android.R.id.content),
                    "landmark was not created. Did you select a landmark?",
                    Snackbar.LENGTH_SHORT
                )
                notification.show()
            }
            Helper.hideSoftKeyboard(this)
        }

        val cancelButton = findViewById<Button>(R.id.editLandmarkCancelButton)
        cancelButton.setOnClickListener {
            startActivity(Intent(this, BaseLandmarkActivity::class.java))
        }
    }
}