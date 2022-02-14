package com.slavik.aproximadorfunciones.mmc.presentacion

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slavik.aproximadorfunciones.mmc.datos.repositorio.Repositorio
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor(
    repositorio: Repositorio
) : ViewModel() {

    var mostrarSplash by mutableStateOf(true)
    private set

    init {
        viewModelScope.launch {
            repositorio.iniciar()
            mostrarSplash = false
        }
    }
}