package com.example.thesimpsonsapp.viewmodels.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.thesimpsonsapp.R
import com.example.thesimpsonsapp.models.Personaje
import com.google.android.material.bottomsheet.BottomSheetDialog

class PersonajeAdapter(
    val context:Context,
    var listaPersonajes: List<Personaje>
):RecyclerView.Adapter<PersonajeAdapter.ViewHolder>() {

    class ViewHolder(item:View):RecyclerView.ViewHolder(item) {
        val cvPersonaje = item.findViewById<CardView>(R.id.cvPersonaje)
        val ivPersonaje = item.findViewById<ImageView>(R.id.ivPersonaje)
        val tvNomPersonaje = item.findViewById<TextView>(R.id.tvNomPersonaje)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_personaje, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val personaje = listaPersonajes[position]

        Glide
            .with(context)
            .load(personaje.imagen)
            .centerInside()
            .into(holder.ivPersonaje)

        holder.tvNomPersonaje.text = personaje.personaje
        holder.cvPersonaje.setOnClickListener {
            mostrarFrase(personaje.frase)
        }

    }

    fun mostrarFrase(frase: String) {
        val bottomSheetDialog = BottomSheetDialog(context)
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_frase)

        val tvFrase = bottomSheetDialog.findViewById<TextView>(R.id.tvFrase)

        tvFrase!!.text = frase

        bottomSheetDialog.show()
    }

    override fun getItemCount(): Int {
        return listaPersonajes.size
    }
}