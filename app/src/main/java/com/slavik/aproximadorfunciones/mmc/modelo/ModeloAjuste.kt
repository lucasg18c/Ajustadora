package com.slavik.aproximadorfunciones.mmc.modelo

import androidx.compose.ui.text.AnnotatedString
import com.slavik.aproximadorfunciones.mmc.modelo.funciones.Funcion
import com.slavik.aproximadorfunciones.mmc.modelo.funciones.Lineal
import com.slavik.aproximadorfunciones.mmc.util.Formato
import com.slavik.aproximadorfunciones.mmc.util.Formato.round
import java.util.*

class ModeloAjuste(
    var puntos : MutableList<Punto> = mutableListOf(),
    var funcion: Funcion = Lineal(),
    var fecha: Calendar = Calendar.getInstance(),
    var nombre : String = "Modelo",
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

    fun getPuntos(): String {
        var puntos = ""

        this.puntos.forEach { punto ->
            puntos += " $punto "
        }
        return puntos
    }

    companion object {
        private var modeloActual: ModeloAjuste? = null

        fun getInstance() : ModeloAjuste {
            if (modeloActual == null) modeloActual = ModeloAjuste()
            return modeloActual!!
        }

        fun resetInstance() {
            modeloActual = null
        }
    }
}