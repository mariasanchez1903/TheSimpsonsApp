package com.example.thesimpsonsapp.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thesimpsonsapp.databinding.ActivitySecondBinding
import com.example.thesimpsonsapp.viewmodels.AnotherViewModel
import com.example.thesimpsonsapp.viewmodels.adapters.AnotherAdapter

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var viewModel: AnotherViewModel
    private lateinit var adapter: AnotherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AnotherViewModel::class.java)
        setupRecyclerView()

        viewModel.obtenerCitas()
        viewModel.listaCitas.observe(this) {
            adapter.itemList = it
            adapter.notifyDataSetChanged()
        }
    }

    private fun setupRecyclerView() {
        binding.rvAnotherList.layoutManager = LinearLayoutManager(this)
        adapter = AnotherAdapter(this, arrayListOf())
        binding.rvAnotherList.adapter = adapter
    }
}