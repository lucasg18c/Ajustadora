package com.slavik.aproximadorfunciones.mmc.ui.screens.editar_modelo_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slavik.aproximadorfunciones.mmc.datos.repositorio.Repositorio
import com.slavik.aproximadorfunciones.mmc.modelo.ModeloAjuste
import com.slavik.aproximadorfunciones.mmc.util.EventoUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditarVM @Inject constructor(
    repositorio: Repositorio
) : ViewModel() {

    var modelo: ModeloAjuste = repositorio.modeloActual
    var x by mutableStateOf(modelo.ejeX)
    var y by mutableStateOf(modelo.ejeY)
    var nombre by mutableStateOf(modelo.nombre)

    private val _evento = Channel<EventoUI>()
    val evento = _evento.receiveAsFlow()

    fun onEvento(evento: EventoEditarModelo) {
        when (evento) {
            is EventoEditarModelo.CambioNombre -> nombre = evento.nombre
            is EventoEditarModelo.CambioX -> x = evento.x
            is EventoEditarModelo.CambioY -> y = evento.y
            is EventoEditarModelo.Guardar -> {
                // todo
                if (x.isNotBlank() && y.isNotBlank() && nombre.isNotBlank()) {

                    modelo.apply {
                        ejeX = x
                        ejeY = y
                        nombre = this@EditarVM.nombre
                    }

                    viewModelScope.launch {
                        _evento.send(EventoUI.Volver)
                    }
                }
            }
        }
    }
}