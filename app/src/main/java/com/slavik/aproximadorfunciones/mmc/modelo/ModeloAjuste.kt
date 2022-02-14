package com.slavik.aproximadorfunciones.mmc.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.slavik.aproximadorfunciones.mmc.modelo.funciones.Funcion
import com.slavik.aproximadorfunciones.mmc.modelo.funciones.Lineal
import com.slavik.aproximadorfunciones.mmc.util.Formato
import java.util.*

@Entity(tableName = "modelos")
class ModeloAjuste(
    @PrimaryKey var mid: Int? = null,
    @ColumnInfo(name = "puntos") var puntos: MutableList<Punto> = mutableListOf(),
    @Ignore var funcion: Funcion = Lineal(),
    var fecha: Calendar = Calendar.getInstance(),
    var nombre: String = "",
    var ejeX: String = "X",
    var ejeY: String = "Y",
) {
    fun fechaAsString(): String {
        return Formato.fecha(fecha)
    }

    fun resolver() {
        funcion.resolver(puntos)
    }

    fun getError(): String {
        return Formato.decimal(funcion.error)
    }

    fun modificado(): Boolean {
        return ejeX != "X" || ejeY != "Y" || nombre.isNotBlank() || puntos.isNotEmpty()
    }

    fun resumen(): String {
        return nombre.ifBlank { puntosString() }
    }

    fun puntosString() : String {
        var puntos = ""

        this.puntos.forEach { punto ->
            puntos += " $punto "
        }

        return puntos
    }
}