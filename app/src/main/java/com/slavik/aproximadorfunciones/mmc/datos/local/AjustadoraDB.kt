package com.slavik.aproximadorfunciones.mmc.datos.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.slavik.aproximadorfunciones.mmc.datos.converter.FechaConverter
import com.slavik.aproximadorfunciones.mmc.datos.converter.ListaPuntosConverter
import com.slavik.aproximadorfunciones.mmc.dominio.modelo.ModeloAjuste

@Database(
    entities = [
        ModeloAjuste::class,
    ],
    version = 1
)
@TypeConverters(
    ListaPuntosConverter::class,
    FechaConverter::class,
)
abstract class AjustadoraDB : RoomDatabase() {
    abstract fun modeloDAO() : ModeloDAO
}