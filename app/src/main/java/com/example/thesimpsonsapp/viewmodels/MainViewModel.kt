package com.example.thesimpsonsapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thesimpsonsapp.core.RetrofitClient
import com.example.thesimpsonsapp.models.Personaje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {


    private val _uiState = MutableLiveData<UiState<List<Personaje>>>()
    val uiState: LiveData<UiState<List<Personaje>>> get() = _uiState

    fun obtenerPersonajes() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val response = RetrofitClient.webService.obtenerPersonajes()
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

    fun obtenerPersonaje(personaje: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val response = RetrofitClient.webService.obtenerPersonaje(personaje)
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