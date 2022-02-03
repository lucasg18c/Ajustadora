package com.slavik.aproximadorfunciones.mmc.modelo.funciones

import com.slavik.aproximadorfunciones.mmc.modelo.Punto
import com.slavik.aproximadorfunciones.mmc.modelo.SEL

class Cuadratica : Funcion() {

    companion object {
        const val NOMBRE = "Cuadrática"
    }

    override fun getSEL(puntos: List<Punto>): SEL {
        TODO("Not yet implemented")
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
        TODO("Not yet implemented")
    }

    override var nombre: String
        get() = NOMBRE
        set(_) {}
}