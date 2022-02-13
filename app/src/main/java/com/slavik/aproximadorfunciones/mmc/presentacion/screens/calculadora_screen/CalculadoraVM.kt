package com.slavik.aproximadorfunciones.mmc.presentacion.screens.calculadora_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slavik.aproximadorfunciones.mmc.dominio.casos_uso.CasosUso
import com.slavik.aproximadorfunciones.mmc.dominio.modelo.ModeloAjuste
import com.slavik.aproximadorfunciones.mmc.dominio.modelo.Punto
import com.slavik.aproximadorfunciones.mmc.util.EventoUI
import com.slavik.aproximadorfunciones.mmc.util.Formato
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculadoraVM @Inject constructor(
    private val casosUso: CasosUso,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _eventoUI = Channel<EventoUI>()
    val eventoUI = _eventoUI.receiveAsFlow()

    var x by mutableStateOf("")
        private set

    var y by mutableStateOf("")
        private set

    var mostrarPuntos by mutableStateOf(false)
        private set

    var puntoSeleccionado by mutableStateOf<Punto?>(null)
        private set

    var modelo by mutableStateOf(ModeloAjuste())
        private set

    init {
        val id = savedStateHandle.get<Int>("id")!!

        viewModelScope.launch {
            casosUso.buscarModelo(id).collect {
                modelo = it
                if (modelo.puntos.isNotEmpty()) modelo.resolver()
            }
        }
    }

    fun onEvento(evento: EventoCalculadora) {
        when (evento) {
            is EventoCalculadora.AgregarEditarPunto -> agregarEditarPunto()
            is EventoCalculadora.CambioPuntoSeleccionado -> cambioPuntoSeleccionado(evento.punto)
            is EventoCalculadora.CambioX -> x = evento.x
            is EventoCalculadora.CambioY -> y = evento.y
            is EventoCalculadora.Cerrar -> {}
            is EventoCalculadora.EliminarPunto -> eliminarPunto()
            is EventoCalculadora.EliminarPuntos -> eliminarPuntos(evento.confirmado)
            is EventoCalculadora.CambioMostrarPuntos -> mostrarPuntos = !mostrarPuntos
        }
    }

    private fun agregarEditarPunto() {
        val xDouble = Formato.aDecimal(x)
        val yDouble = Formato.aDecimal(y)

        if (xDouble.isNaN() || yDouble.isNaN()) {
            // formato incorrecto
            enviarEvento(EventoUI.Snackbar("Formato incorrecto de punto."))
            return
        }

        modelo.puntos.find { punto -> punto.x == xDouble && punto.y == yDouble }?.let {
            // el punto ya existe
            enviarEvento(EventoUI.Snackbar("Ya existe el punto $it."))
            return
        }

        puntoSeleccionado?.let {
            // Modificar punto
            it.x = xDouble
            it.y = yDouble

            limpiarXY()
            puntoSeleccionado = null
            resolver()
            return
        }

        // Nuevo punto
        modelo.puntos.add(Punto(xDouble, yDouble))
        limpiarXY()
        resolver()
    }

    private fun eliminarPunto() {
        modelo.puntos.remove(puntoSeleccionado)
        puntoSeleccionado = null
        limpiarXY()
        resolver()
    }

    private fun eliminarPuntos(confirmado: Boolean) {
        if (!confirmado) {

            // Pedir confirmación
            enviarEvento(
                EventoUI.Snackbar(
                    "Seguro quieres eliminar todos los puntos?",
                    "Eliminar"
                )
            )
            return
        }

        // Confirmado, eliminar puntos
        modelo.puntos = mutableListOf()
        puntoSeleccionado = null
        limpiarXY()
        resolver()

    }

    private fun cambioPuntoSeleccionado(punto: Punto?) {
        puntoSeleccionado = punto
        punto?.let {
            x = Formato.decimal(it.x)
            y = Formato.decimal(it.y)
            return
        }

        limpiarXY()
    }

    private fun enviarEvento(evento: EventoUI) {
        viewModelScope.launch {
            _eventoUI.send(evento)
        }
    }

    private fun limpiarXY() {
        x = ""
        y = ""
    }

    private fun resolver() {
        //modelo.resolver()
        guardarModelo()
    }

    private fun guardarModelo() {
        viewModelScope.launch {
            casosUso.insertarModelo(modelo)
        }
    }
}