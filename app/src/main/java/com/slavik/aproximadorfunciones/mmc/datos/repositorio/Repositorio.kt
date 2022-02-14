package com.slavik.aproximadorfunciones.mmc.datos.repositorio

import com.slavik.aproximadorfunciones.mmc.datos.local.AjustadoraDB
import com.slavik.aproximadorfunciones.mmc.dominio.modelo.ModeloAjuste
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

class Repositorio(
    private val db: AjustadoraDB
) {
    var modelos = mutableListOf<ModeloAjuste>()
    var modeloActual: ModeloAjuste = ModeloAjuste()

    suspend fun iniciar() {
        modelos = db.modeloDAO().buscar().toMutableList()
    }

    fun guardar() {
        runBlocking {
            modelos.forEach { modelo ->
                if (modelo.modificado())
                    db.modeloDAO().insertar(modelo)
            }
        }
    }
}