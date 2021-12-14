package com.iodine.apocmobile.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iodine.apocmobile.R
import com.iodine.apocmobile.models.Location
import com.iodine.apocmobile.utils.DataManager
import com.iodine.apocmobile.utils.inflate

class LocationAdapter(private val locations: ArrayList<Location>)
    : RecyclerView.Adapter<LocationAdapter.LocationHolder>()  {

    var itemClickListener: ((position: Int, name: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationHolder {
        val inflatedView = parent.inflate(R.layout.card_location, false)
        return LocationHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: LocationHolder, position: Int) {
        val element = locations[position]
        holder.locationName.text = element.name
        holder.locationBio.text = element.bio
        holder.locationWorld.text = element.worldName
        holder.itemView.setOnClickListener {
            itemClickListener?.invoke(position, element.toString())
        }
        holder.itemView.isSelected = DataManager.selectedItemIndex == position
    }

    override fun getItemCount() = locations.size

    class LocationHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val locationName = itemView.findViewById(R.id.locationCardTitle) as TextView
        val locationWorld = itemView.findViewById(R.id.locationCardWorldName) as TextView
        val locationBio = itemView.findViewById(R.id.locationCardBioText) as TextView
    }
}