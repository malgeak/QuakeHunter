package com.malgeak.quakehunter.ui.quakehunter.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.malgeak.quakehunter.R
import com.malgeak.quakehunter.data.model.EarthQuake
import com.malgeak.quakehunter.databinding.ItemSismoLayoutBinding
import java.text.SimpleDateFormat
import java.util.*

class ListEarthQuakesAdapter(val listener: OnItemEarthQuakeClickListener): RecyclerView.Adapter<ListEarthQuakesAdapter.ViewHolder>() {
    private var listEarthQuakes: List<EarthQuake> = listOf()

    fun setList(list: List<EarthQuake>) {
        listEarthQuakes = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ItemSismoLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val earthQuake = listEarthQuakes[position]
        val format = SimpleDateFormat("dd/MM/yyyy HH:mm")
        val date = format.format(Date(earthQuake.properties.time))
        val magnitude = earthQuake.properties.mag
        val scale = when (earthQuake.properties.magType) {
            "md" -> "de Duracion"
            else -> "Richter"
        }
        val place = earthQuake.properties.place ?: ""
        val idColor = if (magnitude <= 4) {
            R.color.secondary
        } else if (magnitude > 4 && magnitude <= 6) {
            R.color.yellow
        } else {
            R.color.primaryVariant
        }

        holder.bindind.textViewTitle.setText(earthQuake.properties.title)
        holder.bindind.textViewDescription.setText(
            holder.context.getString(
                R.string.quake_description,
                date,
                magnitude,
                scale,
                place
            )
        )
        holder.bindind.imageView.setColorFilter(holder.context.getColor(idColor))

        holder.bindind.root.setOnClickListener {
            listener.onClickItem(earthQuake)
        }
    }

    override fun getItemCount(): Int {
        return listEarthQuakes.size
    }

    class ViewHolder(val bindind: ItemSismoLayoutBinding, val context: Context) :
        RecyclerView.ViewHolder(bindind.root)
}