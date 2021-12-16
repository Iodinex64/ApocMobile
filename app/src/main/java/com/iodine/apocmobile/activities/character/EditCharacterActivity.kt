package com.iodine.apocmobile.activities.character

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import com.iodine.apocmobile.R
import com.iodine.apocmobile.activities.landmark.DisplayLandmarksActivity
import com.iodine.apocmobile.utils.DataManager
import com.iodine.apocmobile.utils.Helper
import com.iodine.apocmobile.models.Character

class EditCharacterActivity : AppCompatActivity() {

    val SELECT_PICTURE = 200
    lateinit var characterURI: Uri
    lateinit var portrait: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_characters)

        val thisCharacter = DataManager.masterCharacters[DataManager.selectedItemIndex]

        portrait = findViewById(R.id.editCharacterPortrait)

        val imageButton = findViewById<Button>(R.id.editCharacterChangePortraitButton)
        imageButton.setOnClickListener {
            editImageChooser()
        }

        val characterNameText = findViewById<TextView>(R.id.editCharacterNameText)
        val characterBioText = findViewById<TextView>(R.id.editCharacterBioText)
        val characterRaceText = findViewById<TextView>(R.id.editCharacteRaceText)
        val characterWorldText = findViewById<TextView>(R.id.editCharacterWorldName)

        //set stuff from character for editing
        characterNameText.text = thisCharacter.name
        characterBioText.text = thisCharacter.bio
        characterRaceText.text = thisCharacter.race
        characterWorldText.text = thisCharacter.worldName
        characterURI = thisCharacter.URI
        portrait.setImageURI(characterURI)


        val submitButton = findViewById<Button>(R.id.editCharacterSubmitButton)
        submitButton.setOnClickListener {
            var characterExists = false
            for (character in DataManager.masterCharacters) {
                if (character.name == characterNameText.text) {
                    characterExists = true
                    break
                }
            }

            if (characterNameText.text.isNotEmpty() && !characterExists) {

                val c = Character(characterNameText.text.toString(),
                    characterRaceText.text.toString(),
                    characterBioText.text.toString(),
                    thisCharacter.worldName,
                    characterURI
                )

                DataManager.editCharacter(c, DataManager.selectedItemIndex)
                DataManager.saveToJSON(applicationContext)
                startActivity(Intent(this, DisplayCharactersActivity::class.java))
            } else {
                val notification = Snackbar.make(
                    findViewById(android.R.id.content),
                    "character was not edited.",
                    Snackbar.LENGTH_SHORT
                )
                notification.show()
            }
            Helper.hideSoftKeyboard(this)
        }

        val cancelButton = findViewById<Button>(R.id.editCharacterCancelButton)
        cancelButton.setOnClickListener {
            startActivity(Intent(this, BaseCharacterActivity::class.java))
        }

        val submitDeleteButton = findViewById<Button>(R.id.editCharacterDeleteButton)
        submitDeleteButton.setOnClickListener {
            DataManager.removeCharacterAtIndex(DataManager.selectedItemIndex)
            Helper.hideSoftKeyboard(this)
            startActivity(Intent(this, DisplayCharactersActivity::class.java))
        }
    }

    private fun editImageChooser() {
        val i = Intent()
        i.type = "image/*"
        i.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(
                i,
                "Select Picture"
            ),
            SELECT_PICTURE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
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