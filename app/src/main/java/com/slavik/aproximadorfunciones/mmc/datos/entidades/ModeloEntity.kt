package com.slavik.aproximadorfunciones.mmc.datos.entidades

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.slavik.aproximadorfunciones.mmc.dominio.modelo.ModeloAjuste
import com.slavik.aproximadorfunciones.mmc.dominio.modelo.funciones.Lineal
import java.util.*

@Entity(tableName = "modelos")
data class ModeloEntity(
    @PrimaryKey(autoGenerate = true) var mid: Int? = null,
    @Embedded var funcion: FuncionEntity = FuncionEntity(nombreFuncion = Lineal.NOMBRE),
    var fecha: Long = Calendar.getInstance().timeInMillis,
    var nombre: String? = null,
    var ejeX: String = "X",
    var ejeY: String = "Y",
    @Ignore var puntos: List<PuntoEntity> = emptyList()
) {
    fun toModelo(): ModeloAjuste {
        return ModeloAjuste(
            mid = mid,
            nombre = nombre ?: "Modelo $mid",
            ejeX = ejeX,
            ejeY = ejeY,
            fecha = Calendar.getInstance().apply { time = Date(fecha) },
            funcion = funcion.toFuncion(),
            puntos = puntos.map { it.toPunto() }.toMutableList()
        )
    }
}
