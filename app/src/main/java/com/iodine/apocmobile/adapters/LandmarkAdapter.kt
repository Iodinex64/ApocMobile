package com.iodine.apocmobile.adapters

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iodine.apocmobile.R
import com.iodine.apocmobile.models.Landmark
import com.iodine.apocmobile.utils.DataManager
import com.iodine.apocmobile.utils.inflate

class LandmarkAdapter(private val landmarks: ArrayList<Landmark>)
    : RecyclerView.Adapter<LandmarkAdapter.LandmarkHolder>()  {

    var itemClickListener: ((position: Int, name: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandmarkHolder {
        val inflatedView = parent.inflate(R.layout.card_landmark, false)
        return LandmarkHolder(inflatedView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: LandmarkHolder, position: Int) {
        val element = landmarks[position]
        holder.landmarkName.text = element.name
        holder.landmarkBio.text = "Bio: " + element.bio
        holder.landmarkLocation.text = "Location: " + element.locationName
        holder.landmarkPop.text = "Population: " + element.getPop().toString()
        holder.itemView.setOnClickListener {
            itemClickListener?.invoke(position, element.toString())
        }
        holder.itemView.isSelected = DataManager.selectedItemIndex == position
    }

    override fun getItemCount() = landmarks.size

    class LandmarkHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val landmarkName = itemView.findViewById(R.id.landmarkCardTitle) as TextView
        val landmarkLocation = itemView.findViewById(R.id.landmarkCardLocationName) as TextView
        val landmarkBio = itemView.findViewById(R.id.landmarkCardBioText) as TextView
        val landmarkPop = itemView.findViewById(R.id.landmarkCardPopText) as TextView
    }
}