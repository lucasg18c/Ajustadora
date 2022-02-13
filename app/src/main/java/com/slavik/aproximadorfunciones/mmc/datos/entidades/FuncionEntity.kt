package com.slavik.aproximadorfunciones.mmc.datos.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.slavik.aproximadorfunciones.mmc.dominio.modelo.funciones.*

@Entity(tableName = "funciones")
data class FuncionEntity(
    @PrimaryKey(autoGenerate = true) var fid: Int? = null,
    var nombreFuncion: String
) {
    fun toFuncion(): Funcion {
        when (nombreFuncion) {
            Lineal.NOMBRE -> return Lineal()
            Parabola.NOMBRE -> return Parabola()
            Exponencial.NOMBRE -> return Exponencial()
            Logaritmica.NOMBRE -> return Logaritmica()
        }
        return Lineal()
    }
}
