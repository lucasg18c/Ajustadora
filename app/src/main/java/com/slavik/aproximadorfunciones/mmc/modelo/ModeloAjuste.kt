package com.slavik.aproximadorfunciones.mmc.modelo

import com.slavik.aproximadorfunciones.mmc.util.Formato
import java.util.*

class ModeloAjuste(
    var puntos : List<Punto>,
    var funcion: Funcion,
    var fecha: Calendar,
    var nombre : String? = null
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
}