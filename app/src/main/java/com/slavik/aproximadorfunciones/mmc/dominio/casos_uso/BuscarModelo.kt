package com.slavik.aproximadorfunciones.mmc.dominio.casos_uso

import com.slavik.aproximadorfunciones.mmc.datos.local.AjustadoraDB
import com.slavik.aproximadorfunciones.mmc.dominio.modelo.ModeloAjuste
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class BuscarModelo(
    private val db: AjustadoraDB
) {
    suspend operator fun invoke(idModelo: Int) : Flow<ModeloAjuste> {
        if (idModelo == -1) {
            db.modeloDAO().insertar(ModeloAjuste())
            return flowOf(db.modeloDAO().buscarUltimo())
        }
        return flowOf(db.modeloDAO().buscar(idModelo))
    }
}