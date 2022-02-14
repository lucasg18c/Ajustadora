package com.slavik.aproximadorfunciones.mmc.dominio.casos_uso

import com.slavik.aproximadorfunciones.mmc.datos.local.AjustadoraDB
import com.slavik.aproximadorfunciones.mmc.dominio.modelo.ModeloAjuste
import java.util.*

class InsertarModelo(
    private val db: AjustadoraDB
) {
    suspend operator fun invoke(modelo: ModeloAjuste) {
        db.modeloDAO().insertar(modelo.apply { fecha = Calendar.getInstance() })
    }
}