package com.slavik.aproximadorfunciones.mmc.modelo

import com.slavik.aproximadorfunciones.mmc.modelo.funciones.Funcion
import com.slavik.aproximadorfunciones.mmc.modelo.funciones.Lineal
import com.slavik.aproximadorfunciones.mmc.util.Formato
import com.slavik.aproximadorfunciones.mmc.util.Formato.round
import java.util.*

class ModeloAjuste(
    var puntos : MutableList<Punto> = mutableListOf(),
    var funcion: Funcion = Lineal(),
    var fecha: Calendar = Calendar.getInstance(),
    var nombre : String? = null,
    var ejeX : String = "X",
    var ejeY : String = "Y",
) {

    fun resumen(maxCaracteres: Int): String{
        nombre?.let {
            return it.substring(0..maxCaracteres-3) + "..."
        }
        var puntosString = ""

        for (punto in puntos) {
            puntosString += "$punto "
        }

        if (puntosString.length < maxCaracteres)
            return puntosString

        return puntosString.substring(0..maxCaracteres-3) + "..."
    }

    fun cantidadPuntos(): Int {
        return puntos.size
    }

    fun fechaAsString(): String {
        return Formato.fecha(fecha)
    }

    fun resolver() {
        funcion.resolver(puntos)
    }

    fun getError() : String {
        return Formato.decimal(funcion.error)
    }
}