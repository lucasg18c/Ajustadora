package com.slavik.aproximadorfunciones.mmc.dominio.modelo.funciones

import com.slavik.aproximadorfunciones.mmc.dominio.modelo.Punto
import kotlin.math.pow

object Funciones {

    fun xn(puntos: List<Punto>, exponente: Int) : Double {
        var suma = 0.0
        puntos.forEach { p ->
            suma += p.x.pow(exponente)
        }
        return suma
    }

    fun xny(puntos: List<Punto>, exponente: Int) : Double {
        var suma = 0.0
        puntos.forEach { p ->
            suma += (p.x.pow(exponente) * p.y)
        }
        return suma
    }

    fun xy(puntos: List<Punto>) : Double {
        var suma = 0.0
        puntos.forEach { p ->
            suma += (p.x * p.y)
        }
        return suma
    }

    fun y(puntos: List<Punto>) : Double {
        var suma = 0.0
        puntos.forEach { p ->
            suma += p.y
        }
        return suma
    }
    fun x(puntos: List<Punto>) : Double {
        var suma = 0.0
        puntos.forEach { p ->
            suma += p.x
        }
        return suma
    }
}