package com.slavik.aproximadorfunciones.mmc.dominio.casos_uso

import com.slavik.aproximadorfunciones.mmc.datos.local.AjustadoraDB

class LimpiarModelos(
    private val db: AjustadoraDB
) {
    suspend operator fun invoke() {
        db.modeloDAO().eliminarVacios()
    }
}