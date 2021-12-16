package com.iodine.apocmobile.activities.character

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import com.iodine.apocmobile.R
import com.iodine.apocmobile.utils.DataManager
import com.iodine.apocmobile.utils.Helper
import timber.log.Timber
import androidx.core.app.ActivityCompat.startActivityForResult


class CreateCharacterActivity : AppCompatActivity() {

    val SELECT_PICTURE = 200
    lateinit var characterURI: Uri
    lateinit var portrait: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_character)

        portrait = findViewById(R.id.createCharacterPortrait)

        val worldSpinner = findViewById<Spinner>(R.id.createCharacterSelectWorldSpinner)

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

        val imageButton = findViewById<Button>(R.id.createCharacterChangePortraitButton)
        imageButton.setOnClickListener {
            imageChooser()
        }

        val submitButton = findViewById<Button>(R.id.createCharacterSubmitButton)
        submitButton.setOnClickListener {
            val characterNameText = findViewById<TextView>(R.id.createCharacterNameText).text.toString()
            val characterBioText = findViewById<TextView>(R.id.createCharacterBioText).text.toString()
            val characterRaceText = findViewById<TextView>(R.id.createCharacteRaceText).text.toString()

            var characterExists = false
            for (character in DataManager.masterCharacters) {
                if (character.name == characterNameText) {
                    characterExists = true
                    break
                }
            }
            if (characterNameText.isNotEmpty() && !characterExists) {
                DataManager.createCharacter(characterNameText,
                    characterRaceText,
                    DataManager.masterWorlds[DataManager.selectedItemIndex],
                    characterBioText,
                    characterURI)
                DataManager.saveToJSON(applicationContext)
                startActivity(Intent(this, DisplayCharactersActivity::class.java))
            }
            else {
                val notification = Snackbar.make(
                    findViewById(android.R.id.content),
                    "character was not created. Did you select a world?",
                    Snackbar.LENGTH_SHORT
                )
                notification.show()
            }
            Helper.hideSoftKeyboard(this)
        }

        val cancelButton = findViewById<Button>(R.id.createCharacterCancelButton)
        cancelButton.setOnClickListener {
            startActivity(Intent(this, BaseCharacterActivity::class.java))
        }
    }

    private fun imageChooser() {
        val i = Intent()
        i.type = "image/*"
        i.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(
                i,
                "Select Picture"),
            SELECT_PICTURE
        )
    }

    override fun onActivityResult(requestCode:Int, resultCode:Int, data:Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {

            if (requestCode == SELECT_PICTURE) {
                val selectedImageUri: Uri? = data?.data
                if (null != selectedImageUri) {
                    portrait.setImageURI(selectedImageUri)
                    characterURI = selectedImageUri
                }
            }
        }
    }
}