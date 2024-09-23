package com.example.thesimpsonsapp.viewmodels.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thesimpsonsapp.R
import com.example.thesimpsonsapp.models.Personaje

class AnotherAdapter(
    private val context: Context,
    var itemList: List<Personaje>
) : RecyclerView.Adapter<AnotherAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textViewItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_another, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.textView.text = item.frase // Cambia esto si necesitas mostrar algo diferente
    }

    override fun getItemCount(): Int = itemList.size
}