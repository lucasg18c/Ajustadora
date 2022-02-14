package com.slavik.aproximadorfunciones.mmc.util

import com.slavik.aproximadorfunciones.mmc.modelo.ModeloAjuste
import com.slavik.aproximadorfunciones.mmc.modelo.Punto
import com.slavik.aproximadorfunciones.mmc.modelo.funciones.Lineal
import java.util.*

object Pruebas {
    val modeloCompleto = ModeloAjuste(
        puntos = mutableListOf(
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
        funcion = Lineal(),
        fecha = Calendar.getInstance(),
        nombre = "Cambio de temperatura"
    )

    val modeloVacio = ModeloAjuste(
        puntos = mutableListOf(),
        funcion = Lineal(),
        fecha = Calendar.getInstance()
    )

    val modelos = listOf(
        ModeloAjuste(
            mid = 0,
            puntos = mutableListOf(
                Punto(1f, 4f),
                Punto(5f, 8f),
                Punto(7f, 14f),
                Punto(11f, 24f),
            ),
            funcion = Lineal(),
            fecha = Calendar.getInstance()
        ),
        ModeloAjuste(
            mid = 1,
            puntos = mutableListOf(
                Punto(1f, 4f),
            ),
            funcion = Lineal(),
            fecha = Calendar.getInstance()
        ),
        ModeloAjuste(
            mid = 2,
            puntos = mutableListOf(
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
            funcion = Lineal(),
            fecha = Calendar.getInstance(),
            nombre = "Cambio de temperatura"
        ),
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
