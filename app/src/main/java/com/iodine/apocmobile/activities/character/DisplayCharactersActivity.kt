package com.iodine.apocmobile.activities.character

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iodine.apocmobile.R
import com.iodine.apocmobile.activities.character.BaseCharacterActivity
import com.iodine.apocmobile.activities.character.EditCharacterActivity
import com.iodine.apocmobile.adapters.CharacterAdapter
import com.iodine.apocmobile.utils.DataManager

class DisplayCharactersActivity : AppCompatActivity() {
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_characters)

        val characterRecycler = findViewById<RecyclerView>(R.id.characterRecyclerView)

        val characterAdapter = CharacterAdapter(DataManager.masterCharacters)
        characterRecycler.adapter = characterAdapter
        characterRecycler.layoutManager = LinearLayoutManager(this)
        characterAdapter.notifyDataSetChanged()

        //go to edit character activity with selected character
        characterAdapter.itemClickListener = { position, _ ->
            DataManager.selectedItemIndex = position
            val editCharacterIntent = Intent(this, EditCharacterActivity::class.java)
            startActivity(editCharacterIntent)
        }

        val cancelButton = findViewById<Button>(R.id.displayCharactersBackButton)
        cancelButton.setOnClickListener {
            val characterIntent = Intent(this, BaseCharacterActivity::class.java)
            startActivity(characterIntent)
        }
    }
}