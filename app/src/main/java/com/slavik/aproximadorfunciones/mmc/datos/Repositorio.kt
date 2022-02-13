package com.slavik.aproximadorfunciones.mmc.datos

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.slavik.aproximadorfunciones.mmc.dominio.modelo.ModeloAjuste

@SuppressLint("MutableCollectionMutableState")
class Repositorio(
    private val db: AjustadoraDB
) : IRepositorio {

    private var modelos by mutableStateOf(mutableListOf<ModeloAjuste>())

//    init {
//        modelos = db.modeloAjusteDAO().buscar().map { it.toModelo() }.toMutableList()
//
//        modelos.forEach { modelo ->
//            modelo.puntos =
//                db.puntoDAO().buscarPuntos(modelo.id!!).map { it.toPunto() }.toMutableList()
//        }
//    }

    override suspend fun buscarModelos(): List<ModeloAjuste> {
        return modelos
    }

    override fun modeloActual(): ModeloAjuste {
        return modelos.last()
    }

    override fun nuevoModelo(): ModeloAjuste {
        modelos.add(ModeloAjuste())
        return modeloActual()
    }

    override fun updateModelo(modeloAjuste: ModeloAjuste) {
//        modelos[modelos.indexOfFirst { it.id == modeloAjuste.id }] = modeloAjuste
    }

    override fun buscarModelo(id: Int): ModeloAjuste {
//        return db.modeloAjusteDAO().buscar(id).toModelo()
//            .apply {
//                puntos = db.puntoDAO().buscarPuntos(id).map { it.toPunto() }.toMutableList()
//            }
        return ModeloAjuste()
    }
}