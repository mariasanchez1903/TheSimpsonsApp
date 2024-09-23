package com.example.thesimpsonsapp.views

import android.content.Intent
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

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: PersonajeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setupRecyclerView()

        viewModel.obtenerPersonajes()
        viewModel.listaPersonajes.observe(this) {
            adapter.listaPersonajes = it
            adapter.notifyDataSetChanged()
        }

        binding.tilBuscar.setEndIconOnClickListener {
            if (binding.tietBuscar.text.toString() == "") {
                viewModel.obtenerPersonajes()
            } else {
                viewModel.obtenerPersonaje(binding.tietBuscar.text.toString().trim())
            }
        }

        binding.btnOpenSecondActivity.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        binding.rvPersonajes.layoutManager = GridLayoutManager(this, 3)
        adapter = PersonajeAdapter(this, arrayListOf())
        binding.rvPersonajes.adapter = adapter
    }
}