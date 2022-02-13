package com.slavik.aproximadorfunciones.mmc.dominio.casos_uso

import com.slavik.aproximadorfunciones.mmc.datos.AjustadoraDB
import com.slavik.aproximadorfunciones.mmc.dominio.modelo.ModeloAjuste
import kotlinx.coroutines.flow.Flow

class BuscarModelos(
    private val db: AjustadoraDB
) {
    operator fun invoke(): Flow<List<ModeloAjuste>> {
        return db.modeloAjusteDAO().buscar()
    }
}