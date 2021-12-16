package com.iodine.apocmobile.adapters

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iodine.apocmobile.R
import com.iodine.apocmobile.models.Character
import com.iodine.apocmobile.utils.DataManager
import com.iodine.apocmobile.utils.inflate

class CharacterAdapter(private val characters: ArrayList<Character>)
    : RecyclerView.Adapter<CharacterAdapter.CharacterHolder>()  {

    var itemClickListener: ((position: Int, name: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val inflatedView = parent.inflate(R.layout.card_character, false)
        return CharacterHolder(inflatedView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val element = characters[position]
        holder.characterName.text = element.name
        holder.characterRace.text = element.race
        holder.characterBio.text = "Bio: " + element.bio
        holder.characterWorld.text = "World: " + element.worldName
        holder.characterImage.setImageURI(element.URI)
        holder.itemView.setOnClickListener {
            itemClickListener?.invoke(position, element.toString())
        }
        holder.itemView.isSelected = DataManager.selectedItemIndex == position
    }

    override fun getItemCount() = characters.size

    class CharacterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val characterName = itemView.findViewById(R.id.characterCardTitle) as TextView
        val characterRace = itemView.findViewById(R.id.characterCardRaceName) as TextView
        val characterWorld = itemView.findViewById(R.id.characterCardWorldName) as TextView
        val characterBio = itemView.findViewById(R.id.characterCardBioText) as TextView
        val characterImage = itemView.findViewById(R.id.characterCardPortrait) as ImageView
    }
}