package com.slavik.aproximadorfunciones.mmc.presentacion.screens.editar_modelo_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slavik.aproximadorfunciones.mmc.dominio.casos_uso.CasosUso
import com.slavik.aproximadorfunciones.mmc.dominio.modelo.ModeloAjuste
import com.slavik.aproximadorfunciones.mmc.util.EventoUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditarVM @Inject constructor(
    private val casosUso: CasosUso,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    lateinit var modelo: ModeloAjuste
    var x by mutableStateOf("")
    var y by mutableStateOf("")
    var nombre by mutableStateOf("")

    private val _evento = Channel<EventoUI>()
    val evento = _evento.receiveAsFlow()

    init {
        viewModelScope.launch {
            val id = savedStateHandle.get<Int>("id")!!

            casosUso.buscarModelo(id).collect {
                modelo = it

                x = modelo.ejeX
                y = modelo.ejeY
                nombre = modelo.nombre
            }
        }
    }

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
                        casosUso.insertarModelo(modelo)
                        _evento.send(EventoUI.Volver)
                    }
                }
            }
        }
    }
}