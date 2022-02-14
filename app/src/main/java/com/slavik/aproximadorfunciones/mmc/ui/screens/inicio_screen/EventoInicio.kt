package com.slavik.aproximadorfunciones.mmc.ui.screens.inicio_screen

import com.slavik.aproximadorfunciones.mmc.modelo.ModeloAjuste

sealed class EventoInicio {
    data class AbrirModelo(val modelo: ModeloAjuste? = null) : EventoInicio()
}
