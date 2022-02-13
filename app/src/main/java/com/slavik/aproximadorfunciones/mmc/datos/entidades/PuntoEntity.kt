package com.slavik.aproximadorfunciones.mmc.datos.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.slavik.aproximadorfunciones.mmc.dominio.modelo.Punto

@Entity(tableName = "puntos")
data class PuntoEntity(
    @PrimaryKey var pid: Int,
    var idModelo: Int,
    var x: Double,
    var y: Double
) {
    fun toPunto() : Punto {
        return Punto(
            x = x,
            y = y
        )
    }
}
