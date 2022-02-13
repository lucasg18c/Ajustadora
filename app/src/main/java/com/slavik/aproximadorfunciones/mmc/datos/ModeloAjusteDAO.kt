package com.slavik.aproximadorfunciones.mmc.datos

import androidx.room.*
import com.slavik.aproximadorfunciones.mmc.dominio.modelo.ModeloAjuste
import kotlinx.coroutines.flow.Flow

@Dao
interface ModeloAjusteDAO {
    @Query("" +
            "SELECT * " +
            "FROM modelos " +
            "WHERE ejeX != 'X' OR ejeY != 'Y' OR nombre != '' OR puntos != '[]'" +
            "ORDER BY fecha DESC")
    fun buscar(): Flow<List<ModeloAjuste>>

    @Query("SELECT * FROM modelos WHERE mid = :id")
    fun buscar(id: Int): Flow<ModeloAjuste>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(modelo: ModeloAjuste)

    @Query("" +
            "SELECT * " +
            "FROM modelos " +
            "WHERE mid = (" +
            "SELECT MAX(mid) FROM modelos)")
    fun buscarUltimo(): Flow<ModeloAjuste>

    @Query("" +
            "DELETE FROM modelos " +
            "WHERE ejeX = 'X' AND ejeY = 'Y' AND nombre = '' AND puntos = '[]'")
    suspend fun eliminarVacios()
}