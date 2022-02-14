package com.slavik.aproximadorfunciones.mmc.presentacion.screens.inicio_screen

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slavik.aproximadorfunciones.mmc.datos.repositorio.Repositorio
import com.slavik.aproximadorfunciones.mmc.dominio.modelo.ModeloAjuste
import com.slavik.aproximadorfunciones.mmc.presentacion.navegacion.Destino
import com.slavik.aproximadorfunciones.mmc.util.EventoUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class InicioVM @Inject constructor(
    private val repositorio: Repositorio
) : ViewModel() {

    var modelos by mutableStateOf(emptyList<ModeloAjuste>())

    private val _evento = Channel<EventoUI>()
    val evento = _evento.receiveAsFlow()

    init {
        modelos = repositorio.modelos
    }

    fun onEvento(evento: EventoInicio) {
        when (evento) {
            is EventoInicio.AbrirModelo -> {
                repositorio.modeloActual = evento.modelo ?: ModeloAjuste().also {
                    repositorio.modelos.add(it)
                }

                viewModelScope.launch {
                    _evento.send(EventoUI.Navegar(Destino.Calculadora))
                }
            }
        }
    }
}