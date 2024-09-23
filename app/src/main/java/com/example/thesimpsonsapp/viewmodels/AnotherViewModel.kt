package com.example.thesimpsonsapp.viewmodels

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.thesimpsonsapp.R
import com.example.thesimpsonsapp.core.RetrofitClient
import com.example.thesimpsonsapp.models.Personaje
import kotlinx.coroutines.launch


class AnotherViewModel : ViewModel() {

    private val _uiState = MutableLiveData<UiState<List<Personaje>>>()
    val uiState: LiveData<UiState<List<Personaje>>> get() = _uiState

    fun obtenerCitas() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val response =
                    RetrofitClient.webService.obtenerCitas()
                if (response.isSuccessful) {
                    _uiState.value = UiState.Success(response.body() ?: emptyList())
                } else {
                    _uiState.value = UiState.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Exception: ${e.message}")
            }
        }
    }

    fun obtenerCitasPorPersonaje(personaje: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val response =
                    RetrofitClient.webService.obtenerCitasPorPersonaje(personaje)
                if (response.isSuccessful) {
                    _uiState.value = UiState.Success(response.body() ?: emptyList())
                } else {
                    _uiState.value = UiState.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Exception: ${e.message}")
            }
        }
    }

}

