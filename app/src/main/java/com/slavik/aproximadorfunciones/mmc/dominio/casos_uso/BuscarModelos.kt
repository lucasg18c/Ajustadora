package com.slavik.aproximadorfunciones.mmc.dominio.casos_uso

import com.slavik.aproximadorfunciones.mmc.datos.local.AjustadoraDB
import com.slavik.aproximadorfunciones.mmc.dominio.modelo.ModeloAjuste
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class BuscarModelos(
    private val db: AjustadoraDB
) {
    suspend operator fun invoke(): Flow<List<ModeloAjuste>> {
        return flowOf(db.modeloDAO().buscar())
    }
}