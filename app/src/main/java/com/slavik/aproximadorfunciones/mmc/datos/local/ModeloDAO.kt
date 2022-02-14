package com.slavik.aproximadorfunciones.mmc.datos.local

import androidx.room.*
import com.slavik.aproximadorfunciones.mmc.modelo.ModeloAjuste

@Dao
interface ModeloDAO {
    @Query("" +
            "SELECT * " +
            "FROM modelos " +
            "WHERE ejeX != 'X' OR ejeY != 'Y' OR nombre != '' OR puntos != '[]'" +
            "ORDER BY fecha DESC")
    suspend fun buscar(): List<ModeloAjuste>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(modelo: ModeloAjuste)
}