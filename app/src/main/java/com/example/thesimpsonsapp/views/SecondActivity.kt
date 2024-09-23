package com.example.thesimpsonsapp.views

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thesimpsonsapp.databinding.ActivitySecondBinding
import com.example.thesimpsonsapp.viewmodels.AnotherViewModel
import com.example.thesimpsonsapp.viewmodels.UiState
import com.example.thesimpsonsapp.viewmodels.adapters.AnotherAdapter

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private val viewModel: AnotherViewModel by viewModels()
    private lateinit var adapter: AnotherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeUiState()
        val personaje = intent.getStringExtra("personaje") ?: ""
        if (personaje.isNotEmpty()) {
            viewModel.obtenerCitasPorPersonaje(personaje)
        } else {
            viewModel.obtenerCitas()
        }
    }

    private fun setupRecyclerView() {
        binding.rvAnotherList.layoutManager = LinearLayoutManager(this)
        adapter = AnotherAdapter(this, arrayListOf())
        binding.rvAnotherList.adapter = adapter
    }

    private fun observeUiState() {
        viewModel.uiState.observe(this) { state ->
            when (state) {
                is UiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is UiState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    adapter.listaCitas = state.data
                    adapter.notifyDataSetChanged()
                }
                is UiState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, state.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
