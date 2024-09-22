package com.example.thesimpsonsapp.views

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.thesimpsonsapp.R
import com.example.thesimpsonsapp.databinding.ActivityMainBinding
import com.example.thesimpsonsapp.viewmodels.MainViewModel
import com.example.thesimpsonsapp.viewmodels.adapters.PersonajeAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binging: ActivityMainBinding
    private lateinit var  viewModel: MainViewModel
    private  lateinit var adapter: PersonajeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binging = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binging.root)

        viewModel= ViewModelProvider(this)[MainViewModel::class.java]
        setupRecyclerView()

        viewModel.obtenerPersonajes()
        viewModel.listaPersonajes.observe(this){
            adapter.listaPersonajes = it
            adapter.notifyDataSetChanged()
        }
        binging.tilBuscar.setEndIconOnClickListener{
           if (binging.tietBuscar.text.toString() == ""){
               viewModel.obtenerPersonajes()
           }else{
               viewModel.obtenerPersonaje(binging.tietBuscar.text.toString().trim())
           }
        }
    }

    private fun setupRecyclerView() {
        binging.rvPersonajes.layoutManager = GridLayoutManager(this,3)
        adapter = PersonajeAdapter(this, arrayListOf())
        binging.rvPersonajes.adapter = adapter
    }
}