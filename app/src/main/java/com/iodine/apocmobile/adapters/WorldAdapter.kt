package com.iodine.apocmobile.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iodine.apocmobile.R
import com.iodine.apocmobile.models.World
import com.iodine.apocmobile.utils.DataManager
import com.iodine.apocmobile.utils.inflate

class WorldAdapter(private val worlds: ArrayList<World>)
    : RecyclerView.Adapter<WorldAdapter.WorldHolder>()  {

    var itemClickListener: ((position: Int, name: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorldHolder {
        val inflatedView = parent.inflate(R.layout.card_world, false)
        return WorldHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: WorldHolder, position: Int) {
        val element = worlds[position]
        holder.worldName.text = element.name
        holder.locationCount.text = "Locations: " + element.locations.size.toString()
        holder.characterCount.text = "Characters: " + element.characters.size.toString()
        holder.itemView.setOnClickListener {
            itemClickListener?.invoke(position, element.toString())
        }
        holder.itemView.isSelected = DataManager.selectedItemIndex == position
    }

    override fun getItemCount() = worlds.size

    class WorldHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val worldName = itemView.findViewById(R.id.worldCardTitle) as TextView
        val locationCount = itemView.findViewById(R.id.worldCardLocationsCount) as TextView
        val characterCount = itemView.findViewById(R.id.worldCardCharactersCount) as TextView
    }
}