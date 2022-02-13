package com.slavik.aproximadorfunciones.mmc.dominio.casos_uso

import com.slavik.aproximadorfunciones.mmc.datos.AjustadoraDB
import com.slavik.aproximadorfunciones.mmc.dominio.modelo.ModeloAjuste
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.single

class LimpiarModelos(
    private val db: AjustadoraDB
) {
    suspend operator fun invoke() {
        db.modeloAjusteDAO().eliminarVacios()
    }
}