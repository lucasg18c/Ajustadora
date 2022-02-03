package com.slavik.aproximadorfunciones

import com.slavik.aproximadorfunciones.mmc.modelo.Punto
import com.slavik.aproximadorfunciones.mmc.modelo.funciones.Lineal
import org.junit.Assert
import org.junit.Test

class AjusteLinealTest {

    @Test
    fun linealCorrecta1() {

        val funcion = Lineal()

        funcion.resolver(
            listOf(
                Punto(1, 1),
                Punto(3, 3)
            )
        )

        Assert.assertEquals(
            "y = 1 x + 0",
            funcion.getFormula()
        )

        Assert.assertEquals(
            0.0,
            funcion.error,
            0.01
        )
    }

    @Test
    fun linealCorrectaEntreDosPuntosIgualX() {

        val funcion = Lineal()

        funcion.resolver(
            listOf(
                Punto(1, 1),
                Punto(3, 5),
                Punto(3, 3),
            )
        )

        Assert.assertEquals(
            "y = 1,5 x + -0,5",
            funcion.getFormula()
        )

        Assert.assertEquals(
            2.0,
            funcion.error,
            0.01
        )
    }

    @Test
    fun linealUnPunto() {

        val funcion = Lineal()

        funcion.resolver(
            listOf(
                Punto(1, 1)
            )
        )

        Assert.assertEquals(
            "y = 1 x + 0",
            funcion.getFormula()
        )

        Assert.assertEquals(
            0.0,
            funcion.error,
            0.01
        )
    }

    @Test
    fun linealDosPuntosIguales() {

        val funcion = Lineal()

        funcion.resolver(
            listOf(
                Punto(1, 1),
                Punto(1, 1),
            )
        )

        Assert.assertEquals(
            "y = 1 x + 0",
            funcion.getFormula()
        )

        Assert.assertEquals(
            0.0,
            funcion.error,
            0.01
        )
    }

    @Test
    fun linealRectaConstante() {

        val funcion = Lineal()

        funcion.resolver(
            listOf(
                Punto(1, 1),
                Punto(2, 1),
            )
        )

        Assert.assertEquals(
            "y = 0 x + 1",
            funcion.getFormula()
        )

        Assert.assertEquals(
            0.0,
            funcion.error,
            0.01
        )
    }

    @Test
    fun linealRectaConstanteEntreDosPuntos() {

        val funcion = Lineal()

        funcion.resolver(
            listOf(
                Punto(1, 2),
                Punto(1, 0),
            )
        )

        Assert.assertEquals(
            "y = 0 x + 1",
            funcion.getFormula()
        )

        Assert.assertEquals(
            2.0,
            funcion.error,
            0.01
        )
    }

    @Test
    fun linealRectaConstanteMuchosPuntos() {

        val funcion = Lineal()

        funcion.
        resolver(
            listOf(
                Punto(1.0, 1.5),
                Punto(2.0, 1.5),
                Punto(3.0, 1.5),
                Punto(4.0, 1.5),
                Punto(5.0, 1.5)
            )
        )

        Assert.assertEquals(
            "y = 0 x + 1,5",
            funcion.getFormula()
        )

        Assert.assertEquals(
            0.0,
            funcion.error,
            0.01
        )
    }
}