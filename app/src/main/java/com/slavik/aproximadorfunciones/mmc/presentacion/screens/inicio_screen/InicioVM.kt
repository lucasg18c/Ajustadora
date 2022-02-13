package com.slavik.aproximadorfunciones.mmc.presentacion.screens.inicio_screen

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slavik.aproximadorfunciones.mmc.dominio.casos_uso.CasosUso
import com.slavik.aproximadorfunciones.mmc.dominio.modelo.ModeloAjuste
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class InicioVM @Inject constructor(
    private val casosUso: CasosUso
) : ViewModel() {

    var modelos by mutableStateOf(emptyList<ModeloAjuste>())

    fun limpiarModelos() {
        viewModelScope.launch {
            casosUso.limpiarModelos()
        }
    }

    init {
        viewModelScope.launch {
            casosUso.buscarModelos()
                .collect {
                modelos = it
            }
        }
    }
}