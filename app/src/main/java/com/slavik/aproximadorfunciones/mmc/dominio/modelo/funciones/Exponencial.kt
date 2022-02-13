package com.slavik.aproximadorfunciones.mmc.dominio.modelo.funciones

import com.slavik.aproximadorfunciones.R
import com.slavik.aproximadorfunciones.mmc.dominio.modelo.Punto
import com.slavik.aproximadorfunciones.mmc.dominio.modelo.SEL

class Exponencial : Funcion() {
    companion object {
        const val NOMBRE = "Exponencial"
    }

    override val imagen: Int = R.drawable.ic_exponencial

    override fun getSEL(puntos: List<Punto>): SEL {
        TODO("Not yet implemented")
    }

    override fun valuar(x: Double): Double {
        TODO("Not yet implemented")
    }

    override fun getFormula(): String {
        TODO("Not yet implemented")
    }

    override fun prepararPuntos(puntos: MutableList<Punto>): MutableList<Punto> {
        TODO("Not yet implemented")
    }

    override var nombre: String = NOMBRE
}