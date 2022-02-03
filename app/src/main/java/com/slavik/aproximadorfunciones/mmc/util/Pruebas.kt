package com.slavik.aproximadorfunciones.mmc.util

import com.slavik.aproximadorfunciones.mmc.modelo.funciones.Funcion
import com.slavik.aproximadorfunciones.mmc.modelo.ModeloAjuste
import com.slavik.aproximadorfunciones.mmc.modelo.Punto
import com.slavik.aproximadorfunciones.mmc.modelo.funciones.Lineal
import java.util.*

object Pruebas {
    val modeloCompleto = ModeloAjuste(
        mutableListOf(
            Punto(1f, 4f),
            Punto(5f, 8f),
            Punto(-7f, 14f),
            Punto(11f, 24f),
            Punto(5f, 8f),
            Punto(7f, 14f),
            Punto(11f, 24f),
            Punto(1f, 4f),
            Punto(5f, 8f),
            Punto(-7f, 14f),
            Punto(11f, 24f),
            Punto(5f, 8f),
            Punto(7f, 14f),
            Punto(11f, 24f),
        ),
        Lineal(),
        Calendar.getInstance(),
        nombre = "Cambio de temperatura"
    )

    val modeloVacio =  ModeloAjuste(
        mutableListOf(),
        Lineal(),
        Calendar.getInstance()
    )

    val modelos = listOf(
        ModeloAjuste(
            mutableListOf(
                Punto(1f, 4f),
                Punto(5f, 8f),
                Punto(7f, 14f),
                Punto(11f, 24f),
            ),
            Lineal(),
            Calendar.getInstance()
        ),
        ModeloAjuste(
            mutableListOf(
                Punto(1f, 4f),
            ),
            Lineal(),
            Calendar.getInstance()
        ),
        ModeloAjuste(
            mutableListOf(
                Punto(1f, 4f),
                Punto(5f, 8f),
                Punto(-7f, 14f),
                Punto(11f, 24f),
                Punto(5f, 8f),
                Punto(7f, 14f),
                Punto(11f, 24f),
                Punto(1f, 4f),
                Punto(5f, 8f),
                Punto(-7f, 14f),
                Punto(11f, 24f),
                Punto(5f, 8f),
                Punto(7f, 14f),
                Punto(11f, 24f),
            ),
            Lineal(),
            Calendar.getInstance(),
            nombre = "Cambio de temperatura"
        ),
        ModeloAjuste(
            mutableListOf(
                Punto(1f, 4f),
                Punto(5f, 8f),
                Punto(7f, 14f),
                Punto(11f, 24f),
            ),
            Lineal(),
            Calendar.getInstance()
        ),
        ModeloAjuste(
            mutableListOf(
                Punto(1f, 4f),
                Punto(5f, 8f),
            ),
            Lineal(),
            Calendar.getInstance()
        ),
        ModeloAjuste(
            mutableListOf(
                Punto(1f, 4f),
                Punto(5f, 8f),
                Punto(7f, 14f),
                Punto(11f, 24f),
                Punto(5f, 8f),
                Punto(7f, 14f),
                Punto(11f, 24f),
            ),
            Lineal(),
            Calendar.getInstance(),
            nombre = "Cambio de temperatura"
        )
    )
    val puntos = listOf(
        Punto(1f, 4f),
        Punto(5f, 8f),
        Punto(7f, 14f),
        Punto(11f, 24f),
        Punto(5f, 8f),
        Punto(7f, 14f),
        Punto(11f, 24f),
    )
}
