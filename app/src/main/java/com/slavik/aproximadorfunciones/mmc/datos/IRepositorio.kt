package com.slavik.aproximadorfunciones.mmc.datos

import com.slavik.aproximadorfunciones.mmc.dominio.modelo.ModeloAjuste

interface IRepositorio {

    suspend fun buscarModelos(): List<ModeloAjuste>

    fun modeloActual(): ModeloAjuste

    fun nuevoModelo(): ModeloAjuste

    fun updateModelo(modeloAjuste: ModeloAjuste)

    fun buscarModelo(id: Int): ModeloAjuste
}