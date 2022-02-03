package com.slavik.aproximadorfunciones.mmc.presentacion.screens.calculadora_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slavik.aproximadorfunciones.mmc.modelo.ModeloAjuste
import com.slavik.aproximadorfunciones.mmc.modelo.Punto
import com.slavik.aproximadorfunciones.mmc.util.EventoUI
import com.slavik.aproximadorfunciones.mmc.util.Formato
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CalculadoraVM : ViewModel() {

    private val _eventoUI = Channel<EventoUI>()
    val eventoUI = _eventoUI.receiveAsFlow()

    var x by mutableStateOf("")
        private set

    var y by mutableStateOf("")
        private set

    var puntoSeleccionado by mutableStateOf<Punto?>(null)
        private set

    var puntos by mutableStateOf(mutableListOf<Punto>())
        private set

    var modelo by mutableStateOf(iniciarModelo())
        private set

    private fun iniciarModelo(): ModeloAjuste {
        return ModeloAjuste()
    }

    fun agregarEditarPunto() {
        val xDouble = Formato.aDecimal(x)
        val yDouble = Formato.aDecimal(y)

        if (xDouble.isNaN() || yDouble.isNaN()) {
            // formato incorrecto
            enviarEvento(EventoUI.Snackbar("Formato incorrecto de punto."))
            return
        }

        puntos.find { punto -> punto.x == xDouble && punto.y == yDouble }?.let {
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
        puntos.add(Punto(xDouble, yDouble))
        limpiarXY()
        resolver()
    }

    fun eliminarPunto() {
        puntos.remove(puntoSeleccionado)
        puntoSeleccionado = null
        limpiarXY()
        resolver()
    }

    fun eliminarPuntos() {
        enviarEvento(
            EventoUI.Snackbar(
                "Seguro quieres eliminar todos los puntos?",
                "Eliminar"
            )
        )
    }

    fun cambioX(x: String) {
        this.x = x
    }

    fun cambioY(y: String) {
        this.y = y
    }

    fun cambioPuntoSeleccionado(punto: Punto?) {
        puntoSeleccionado = punto
        punto?.let {
            x = Formato.decimal(it.x)
            y = Formato.decimal(it.y)
            return
        }

        limpiarXY()
    }

    fun onDispose() {

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

    fun eliminarPuntosConfirmado() {
        puntos = mutableListOf()
        puntoSeleccionado = null
        limpiarXY()
        resolver()
    }

    private fun resolver(){
        modelo.puntos = puntos

        modelo.resolver()
    }
}