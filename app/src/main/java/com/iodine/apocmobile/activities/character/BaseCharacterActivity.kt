package com.iodine.apocmobile.activities.character

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.iodine.apocmobile.R
import com.iodine.apocmobile.activities.main.MainActivity

class BaseCharacterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_character)

        val backButton = findViewById<Button>(R.id.charactersBackButton)
        backButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val createButton = findViewById<Button>(R.id.createcharacterButton)
        createButton.setOnClickListener {
            startActivity(Intent(this, CreateCharacterActivity::class.java))
        }

        val editButton = findViewById<Button>(R.id.editcharactersButton)
        editButton.setOnClickListener {
            startActivity(Intent(this, DisplayCharactersActivity::class.java))
        }
    }
}