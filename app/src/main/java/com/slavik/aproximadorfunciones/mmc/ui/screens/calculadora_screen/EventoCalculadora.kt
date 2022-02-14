package com.slavik.aproximadorfunciones.mmc.ui.screens.calculadora_screen

import com.slavik.aproximadorfunciones.mmc.modelo.Punto

sealed class EventoCalculadora {
    object AgregarEditarPunto : EventoCalculadora()
    data class EliminarPuntos(val confirmado: Boolean) : EventoCalculadora()
    object EliminarPunto : EventoCalculadora()
    data class CambioX(val x: String) : EventoCalculadora()
    data class CambioY(val y: String) : EventoCalculadora()
    data class CambioPuntoSeleccionado(val punto: Punto?) : EventoCalculadora()
    object CambioMostrarPuntos : EventoCalculadora()
}
