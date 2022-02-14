package com.slavik.aproximadorfunciones.mmc.presentacion.screens.inicio_screen

import com.slavik.aproximadorfunciones.mmc.dominio.modelo.ModeloAjuste

sealed class EventoInicio {
    data class AbrirModelo(val modelo: ModeloAjuste? = null) : EventoInicio()
}
