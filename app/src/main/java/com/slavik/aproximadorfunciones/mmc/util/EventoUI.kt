package com.slavik.aproximadorfunciones.mmc.util

import com.slavik.aproximadorfunciones.mmc.presentacion.navegacion.Destino

sealed class EventoUI {
    data class Snackbar(val mensaje: String, val action: String? = null) : EventoUI()
    data class Navegar(val destino: Destino): EventoUI()
    object Volver : EventoUI()
}
