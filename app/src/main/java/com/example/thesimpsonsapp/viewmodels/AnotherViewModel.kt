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

class AnotherViewModel:ViewModel() {

    private var _listaCitas = MutableLiveData<List<Personaje>>()
    val listaCitas: LiveData<List<Personaje>> get() = _listaCitas

    fun obtenerCitas() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.obtenerCitas()
            withContext(Dispatchers.Main) {
                Log.d("API", response.body().toString())
                _listaCitas.value = response.body()
            }
        }
    }

    fun obtenerCitasPorPersonaje(personaje: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.obtenerCitasPorPersonaje(personaje)
            withContext(Dispatchers.Main) {
                Log.d("API", response.body().toString())
                _listaCitas.value = response.body()
            }
        }
    }
}