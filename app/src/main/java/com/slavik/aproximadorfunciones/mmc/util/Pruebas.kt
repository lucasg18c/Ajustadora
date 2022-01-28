package com.slavik.aproximadorfunciones.mmc.util

import com.slavik.aproximadorfunciones.mmc.modelo.Funcion
import com.slavik.aproximadorfunciones.mmc.modelo.ModeloAjuste
import com.slavik.aproximadorfunciones.mmc.modelo.Punto
import java.util.*

object Pruebas {
    val modeloCompleto = ModeloAjuste(
        listOf(
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
        Funcion(),
        Calendar.getInstance(),
        nombre = "Cambio de temperatura"
    )

    val modeloVacio =  ModeloAjuste(
        listOf(),
        Funcion(),
        Calendar.getInstance()
    )

    val modelos = listOf(
        ModeloAjuste(
            listOf(
                Punto(1f, 4f),
                Punto(5f, 8f),
                Punto(7f, 14f),
                Punto(11f, 24f),
            ),
            Funcion(),
            Calendar.getInstance()
        ),
        ModeloAjuste(
            listOf(
                Punto(1f, 4f),
            ),
            Funcion(),
            Calendar.getInstance()
        ),
        ModeloAjuste(
            listOf(
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
            Funcion(),
            Calendar.getInstance(),
            nombre = "Cambio de temperatura"
        ),
        ModeloAjuste(
            listOf(
                Punto(1f, 4f),
                Punto(5f, 8f),
                Punto(7f, 14f),
                Punto(11f, 24f),
            ),
            Funcion(),
            Calendar.getInstance()
        ),
        ModeloAjuste(
            listOf(
                Punto(1f, 4f),
                Punto(5f, 8f),
            ),
            Funcion(),
            Calendar.getInstance()
        ),
        ModeloAjuste(
            listOf(
                Punto(1f, 4f),
                Punto(5f, 8f),
                Punto(7f, 14f),
                Punto(11f, 24f),
                Punto(5f, 8f),
                Punto(7f, 14f),
                Punto(11f, 24f),
            ),
            Funcion(),
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
