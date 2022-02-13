package com.slavik.aproximadorfunciones.mmc.dominio.casos_uso

import com.slavik.aproximadorfunciones.mmc.datos.AjustadoraDB
import com.slavik.aproximadorfunciones.mmc.dominio.modelo.ModeloAjuste
import kotlinx.coroutines.flow.Flow

class BuscarModelo(
    private val db: AjustadoraDB
) {
    suspend operator fun invoke(idModelo: Int) : Flow<ModeloAjuste> {
        if (idModelo == -1) {
            db.modeloAjusteDAO().insertar(ModeloAjuste())
            return db.modeloAjusteDAO().buscarUltimo()
        }
        return db.modeloAjusteDAO().buscar(idModelo)
    }
}