package com.example.thesimpsonsapp.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.thesimpsonsapp.databinding.ActivityMainBinding
import com.example.thesimpsonsapp.viewmodels.MainViewModel
import com.example.thesimpsonsapp.viewmodels.UiState
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

        viewModel.uiState.observe(this) { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is UiState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    adapter.listaPersonajes = uiState.data
                    adapter.notifyDataSetChanged()
                }
                is UiState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, uiState.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.tilBuscar.setEndIconOnClickListener {
            if (binding.tietBuscar.text.toString().trim().isEmpty()) {
                viewModel.obtenerPersonajes()
            } else {
                viewModel.obtenerPersonaje(binding.tietBuscar.text.toString().trim())
            }
        }
        binding.btnOpenSecondActivity.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            // Pasar datos si es necesario
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        binding.rvPersonajes.layoutManager = GridLayoutManager(this, 3)
        adapter = PersonajeAdapter(this, arrayListOf())
        binding.rvPersonajes.adapter = adapter
    }
}
