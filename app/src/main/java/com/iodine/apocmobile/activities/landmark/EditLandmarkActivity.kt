package com.iodine.apocmobile.activities.landmark

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.iodine.apocmobile.R
import com.iodine.apocmobile.activities.landmark.BaseLandmarkActivity
import com.iodine.apocmobile.activities.landmark.DisplayLandmarksActivity
import com.iodine.apocmobile.models.Landmark
import com.iodine.apocmobile.utils.DataManager
import com.iodine.apocmobile.utils.Helper

class EditLandmarkActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_landmark)


        val thisLandmark = DataManager.masterLandmarks[DataManager.selectedItemIndex]

        val landmarkNameText = findViewById<TextView>(R.id.editLandmarkNameText)
        val landmarkBioText = findViewById<TextView>(R.id.editLandmarkBioText)
        val landmarkPopText = findViewById<TextView>(R.id.editLandmarkPopulationNumber)
        val landmarkLocationText = findViewById<TextView>(R.id.editLandmarkLocationName)

        //edit related assignments
        landmarkNameText.text = thisLandmark.name
        landmarkBioText.text = thisLandmark.bio
        landmarkPopText.text = thisLandmark.getPop().toString()
        landmarkLocationText.text = "This landmark belongs to " + thisLandmark.locationName

        val submitButton = findViewById<Button>(R.id.editLandmarkSubmitButton)
        submitButton.setOnClickListener {
            var landmarkExists = false
            for (landmark in DataManager.masterLandmarks) {
                if (landmark.name == landmarkNameText.text) {
                    landmarkExists = true
                    break
                }
            }
            if (landmarkNameText.text.isNotEmpty() && !landmarkExists) {
                val l = Landmark(landmarkNameText.text.toString(),
                    landmarkBioText.text.toString(),
                    thisLandmark.locationName)

                DataManager.editLandmark(l, DataManager.selectedItemIndex)
                DataManager.saveToJSON(applicationContext)
                startActivity(Intent(this, DisplayLandmarksActivity::class.java))
            }
            else {
                val notification = Snackbar.make(
                    findViewById(android.R.id.content),
                    "landmark was not edited.",
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

        val submitDeleteButton = findViewById<Button>(R.id.editLandmarkDeleteButton)
        submitDeleteButton.setOnClickListener {
            DataManager.removeLandmarkAtIndex(DataManager.selectedItemIndex)
            Helper.hideSoftKeyboard(this)
            startActivity(Intent(this, DisplayLandmarksActivity::class.java))
        }
    }
}