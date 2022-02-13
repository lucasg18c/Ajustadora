package com.slavik.aproximadorfunciones.mmc.presentacion.screens.calculadora_screen

import com.slavik.aproximadorfunciones.mmc.dominio.modelo.Punto

sealed class EventoCalculadora {
    object AgregarEditarPunto : EventoCalculadora()
    data class EliminarPuntos(val confirmado: Boolean) : EventoCalculadora()
    object EliminarPunto : EventoCalculadora()
    data class CambioX(val x: String) : EventoCalculadora()
    data class CambioY(val y: String) : EventoCalculadora()
    data class CambioPuntoSeleccionado(val punto: Punto?) : EventoCalculadora()

    object CambioMostrarPuntos : EventoCalculadora()
    object Cerrar : EventoCalculadora()
}
