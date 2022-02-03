package com.slavik.aproximadorfunciones.mmc.modelo.funciones

import com.slavik.aproximadorfunciones.mmc.modelo.Punto
import com.slavik.aproximadorfunciones.mmc.modelo.SEL
import com.slavik.aproximadorfunciones.mmc.util.Formato

class Lineal : Funcion() {
    companion object {
        const val NOMBRE = "Lineal"
    }

    override fun valuar(x: Double): Double {
        return coeficientes?.let { c ->
            c[0] * x + c[1]

        } ?: Double.NaN
    }

    override fun getFormula(): String {
        return coeficientes?.let { c ->
            "y = ${Formato.decimal(c[0])} x + ${Formato.decimal(c[1])}"

        } ?: "y = ax + b"
    }

    override fun prepararPuntos(puntos: MutableList<Punto>): MutableList<Punto> {
        // Sin puntos
        if (puntos.size < 1) return mutableListOf()

        // Un único punto
        if (puntos.size == 1) {
            val punto = puntos[0]
            puntos.add(
                Punto(
                    punto.x + 1,
                    punto.y + 1,
                    visible = false
                )
            )
        }
        else {
            // Comprobar si todos los puntos tienen igual x
            var x: Double? = null
            var xIguales = true

            for (punto in puntos) {
                if (x == null){
                    x = punto.x
                }

                // Si hay x distinto no hay problema
                else if (punto.x != x){
                    xIguales = false
                    break
                }
            }

            // Todos los x son iguales
            if (xIguales) {
                var yPromedio = 0.0
                for (punto in puntos) {
                    yPromedio += punto.y
                }
                yPromedio /= puntos.size

                puntos.add(Punto(
                    x = x!! + 1,
                    y = yPromedio,
                    visible = false
                ))
            }
        }
        return puntos
    }

    override var nombre: String
        get() = NOMBRE
        set(_) {}

    override fun getSEL(puntos: List<Punto>): SEL {
        return SEL(
            coeficientes = mutableListOf(
                mutableListOf(
                    Funciones.xn(puntos, 2),
                    Funciones.x(puntos),
                    Funciones.xy(puntos)
                ),
                mutableListOf(
                    Funciones.x(puntos),
                    puntos.size.toDouble(),
                    Funciones.y(puntos)
                )
            )
        )
    }
}
