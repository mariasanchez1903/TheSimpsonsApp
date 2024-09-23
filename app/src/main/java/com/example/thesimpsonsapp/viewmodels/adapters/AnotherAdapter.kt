package com.example.thesimpsonsapp.viewmodels.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.thesimpsonsapp.R
import com.example.thesimpsonsapp.models.Personaje

class AnotherAdapter(
    private val context: Context,
    var listaCitas: List<Personaje>
) : RecyclerView.Adapter<AnotherAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.ivCita)
        val textView: TextView = itemView.findViewById(R.id.tvCita)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_another, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cita = listaCitas[position]

        holder.textView.text = cita.frase
        Glide.with(context)
            .load(cita.imagen)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return listaCitas.size
    }
}