package com.slavik.aproximadorfunciones.mmc.modelo.funciones

import com.slavik.aproximadorfunciones.mmc.modelo.Punto
import com.slavik.aproximadorfunciones.mmc.modelo.SEL
import kotlin.math.pow

abstract class Funcion {
    protected var coeficientes: List<Double>? = null

    var error: Double = 0.0
        private set

    var base: Double = 1.0

    abstract fun getSEL(puntos: List<Punto>): SEL
    abstract fun valuar(x: Double): Double

    abstract fun getFormula(): String
    abstract fun prepararPuntos(puntos: MutableList<Punto>): MutableList<Punto>

    abstract var nombre: String
        protected set

    fun resolver(puntos: List<Punto>) : List<Punto> {
        val puntosCopy = preparacionInicial(puntos)

        coeficientes = getSEL(puntosCopy).solucion()

        calcularError(puntos)

        return puntosCopy
    }

    private fun preparacionInicial(puntos: List<Punto>): MutableList<Punto> {

        var puntosCopy = puntos
            .filter { it.visible }
            .distinctBy { "${it.x} ${it.y}" }
            .toMutableList()

        puntosCopy = prepararPuntos(puntosCopy)
        puntosCopy.sortBy{it.x}
        return puntosCopy

    }

    private fun calcularError(puntos: List<Punto>) {
        error = 0.0

        for (punto in puntos) {
            error += (punto.y - valuar(punto.x)).pow(2)
        }

        error /= puntos.size
    }

    fun setCoeficientes(vararg coeficientes: Int) {
        this.coeficientes = coeficientes.asList().map { it.toDouble() }
    }
}