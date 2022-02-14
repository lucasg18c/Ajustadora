package com.slavik.aproximadorfunciones.mmc.ui.screens.formas_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slavik.aproximadorfunciones.mmc.datos.repositorio.Repositorio
import com.slavik.aproximadorfunciones.mmc.modelo.funciones.Funcion
import com.slavik.aproximadorfunciones.mmc.util.EventoUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormasVM @Inject constructor(
    private val repositorio: Repositorio
) : ViewModel() {

    private val _evento = Channel<EventoUI>()
    val evento = _evento.receiveAsFlow()

    fun elegirForma(funcion: Funcion) {
        repositorio.modeloActual.funcion = funcion
        viewModelScope.launch {
            _evento.send(EventoUI.Volver)
        }
    }
}