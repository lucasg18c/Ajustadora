package com.slavik.aproximadorfunciones.mmc.modelo

import kotlin.math.round

class SEL(
    private val coeficientes: MutableList<MutableList<Double>>,
    private val strategy: IstrategySEL = Gauss()
) {

    fun solucion() : List<Double> {
        return strategy.resolver(coeficientes)
    }

    interface IstrategySEL {
        fun resolver(sel: MutableList<MutableList<Double>>) : List<Double>
    }

    class Gauss : IstrategySEL {
        fun Double.round(decimals: Int): Double {
            var multiplier = 1.0
            repeat(decimals) { multiplier *= 10 }
            return round(this * multiplier) / multiplier
        }

        override fun resolver(sel: MutableList<MutableList<Double>>): List<Double> {
            var f: Int
            var v: Double
            val m = sel.size
            val n = sel[0].size

            // Reducción de la matriz
            for (col in 0 .. n - 3) {
                for (fila in col + 1 until m) {
                    if (sel[fila][col] == 0.0) continue

                    f = fila - 1
                    v = -sel[fila][col]/sel[f][col]

                    for (k in col until n) {
                        sel[fila][k] += sel[f][k] * v
                    }
                }
            }

            // Intentar resolver sistema de ecuaciones
            try {
                val solucion = MutableList(m) { 0.0 }
                var suma: Double

                for (fila in m - 1 downTo 0) {
                    suma = 0.0

                    if (fila < m - 1) {
                        for (col in n - 2 downTo fila + 1) {
                            suma += sel[fila][col] * solucion[fila + 1]
                        }
                    }
                    solucion[fila] = ((sel[fila][n - 1] - suma) / sel[fila][fila]).round(8)
                }
                return solucion

            } catch (e: Exception) {
                e.printStackTrace()
                return emptyList()
            }
        }
    }
}