package com.slavik.aproximadorfunciones.mmc.dominio.casos_uso

import com.slavik.aproximadorfunciones.mmc.datos.AjustadoraDB

class CasosUso(
    private val db: AjustadoraDB
) {
    val buscarModelo = BuscarModelo(db)
    val buscarModelos = BuscarModelos(db)
    val insertarModelo = InsertarModelo(db)
    val limpiarModelos = LimpiarModelos(db)
}