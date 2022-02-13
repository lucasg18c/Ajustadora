package com.slavik.aproximadorfunciones.mmc.dominio.modelo.funciones

import com.slavik.aproximadorfunciones.R
import com.slavik.aproximadorfunciones.mmc.dominio.modelo.Punto
import com.slavik.aproximadorfunciones.mmc.dominio.modelo.SEL

class Parabola : Funcion() {

    companion object {
        const val NOMBRE = "Parábola"
    }

    override val imagen: Int = R.drawable.ic_parabola

    override fun getSEL(puntos: List<Punto>): SEL {
        return SEL(
            mutableListOf(
                mutableListOf(
                    Funciones.xn(puntos, 4),
                    Funciones.xn(puntos, 3),
                    Funciones.xn(puntos, 2),
                    Funciones.xny(puntos, 2),
                ),
                mutableListOf(
                    Funciones.xn(puntos, 3),
                    Funciones.xn(puntos, 2),
                    Funciones.x(puntos),
                    Funciones.xy(puntos),
                ),
                mutableListOf(
                    Funciones.xn(puntos, 2),
                    Funciones.x(puntos),
                    puntos.size.toDouble(),
                    Funciones.y(puntos),
                )
            )
        )
    }

    override fun valuar(x: Double): Double {
        return coeficientes?.let { c ->
            c[0] * x * x + c[1] * x + c[2]
        } ?: Double.NaN
    }

    override fun getFormula(): String {
        return coeficientes?.let { c->
            "y = ${c[0]}x^2 + ${c[1]}x + ${c[2]}"
        } ?: "y = ax^2 + bx + c"
    }

    override fun prepararPuntos(puntos: MutableList<Punto>): MutableList<Punto> {
        return puntos // todo implementar
    }

    override val nombre: String = NOMBRE
}