package com.example.elsol

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TarjetaSolAdapter (
    private val solarImages: List<TarjetaSol>,
    private val onMenuItemClick: (MenuItem, TarjetaSol) -> Unit
) : RecyclerView.Adapter<TarjetaSolAdapter.SolarImageViewHolder>() {

    class SolarImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val imageName: TextView = view.findViewById(R.id.imageName)
        val toolbar: androidx.appcompat.widget.Toolbar = view.findViewById(R.id.itemToolbar)

        fun bind(solarImage: TarjetaSol, onMenuItemClick: (MenuItem, TarjetaSol) -> Unit) {
            imageView.setImageResource(solarImage.imagenSol)
            imageName.text = solarImage.nombre

            toolbar.setOnMenuItemClickListener {
                onMenuItemClick(it, solarImage)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SolarImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sol, parent, false)
        return SolarImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: SolarImageViewHolder, position: Int) {
        val solarImage = solarImages[position]
        holder.toolbar.inflateMenu(R.menu.menu_item)
        holder.bind(solarImage, onMenuItemClick)
    }

    override fun getItemCount(): Int = solarImages.size
}