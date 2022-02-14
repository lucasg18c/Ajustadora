package com.slavik.aproximadorfunciones

import com.slavik.aproximadorfunciones.mmc.modelo.Punto
import com.slavik.aproximadorfunciones.mmc.modelo.funciones.Lineal
import org.junit.Assert.*
import org.junit.Test

class FormatoLinealTest {

    @Test
    fun a1b0() {
        val funcion = Lineal()
        funcion.resolver(
            listOf(
                Punto(0, 0),
                Punto(1, 1),
            )
        )

        assertEquals(
            "y = x",
            funcion.getFormula()
        )
    }

    @Test
    fun a1b1() {
        val funcion = Lineal()
        funcion.resolver(
            listOf(
                Punto(0, 1),
                Punto(1, 2),
            )
        )

        assertEquals(
            "y = x + 1",
            funcion.getFormula()
        )
    }

    @Test
    fun amenos1b0() {
        val funcion = Lineal()
        funcion.resolver(
            listOf(
                Punto(0, 0),
                Punto(1, -1),
            )
        )

        assertEquals(
            "y = - x",
            funcion.getFormula()
        )
    }

    @Test
    fun amenos1b1() {
        val funcion = Lineal()
        funcion.resolver(
            listOf(
                Punto(0, 1),
                Punto(1, 0),
            )
        )

        assertEquals(
            "y = - x + 1",
            funcion.getFormula()
        )
    }

    @Test
    fun a1b0Con1Punto() {
        val funcion = Lineal()
        funcion.resolver(
            listOf(
                Punto(0, 0)
            )
        )

        assertEquals(
            "y = x",
            funcion.getFormula()
        )
    }

    @Test
    fun a0b0Con2Puntos() {
        val funcion = Lineal()
        funcion.resolver(
            listOf(
                Punto(0, 0),
                Punto(1, 0)
            )
        )

        assertEquals(
            "y = 0",
            funcion.getFormula()
        )
    }

    @Test
    fun a0b0Con2PuntosEnMismoX() {
        val funcion = Lineal()
        funcion.resolver(
            listOf(
                Punto(1, 1),
                Punto(1, -1)
            )
        )

        assertEquals(
            "y = 0",
            funcion.getFormula()
        )
    }

    @Test
    fun a2b1() {
        val funcion = Lineal()
        funcion.resolver(
            listOf(
                Punto(0, 1),
                Punto(1, 3)
            )
        )

        assertEquals(
            "y = 2 x + 1",
            funcion.getFormula()
        )
    }


}