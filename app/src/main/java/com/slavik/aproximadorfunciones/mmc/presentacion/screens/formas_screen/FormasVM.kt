package com.slavik.aproximadorfunciones.mmc.presentacion.screens.formas_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slavik.aproximadorfunciones.mmc.modelo.ModeloAjuste
import com.slavik.aproximadorfunciones.mmc.modelo.funciones.Funcion
import com.slavik.aproximadorfunciones.mmc.util.EventoUI
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class FormasVM : ViewModel() {

    private val _evento = Channel<EventoUI>()
    val evento = _evento.receiveAsFlow()

    fun formaElegida(forma: Funcion) {
        viewModelScope.launch {
            ModeloAjuste.getInstance().funcion = forma
            _evento.send(EventoUI.Volver)
        }
    }
}