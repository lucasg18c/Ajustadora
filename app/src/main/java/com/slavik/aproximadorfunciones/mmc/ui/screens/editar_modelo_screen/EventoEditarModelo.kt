package com.slavik.aproximadorfunciones.mmc.ui.screens.editar_modelo_screen

sealed class EventoEditarModelo {
    data class CambioX(val x: String) : EventoEditarModelo()
    data class CambioY(val y: String) : EventoEditarModelo()
    data class CambioNombre(val nombre: String) : EventoEditarModelo()
    object Guardar : EventoEditarModelo()
}
