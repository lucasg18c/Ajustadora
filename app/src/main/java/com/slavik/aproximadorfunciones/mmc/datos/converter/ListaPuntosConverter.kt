package com.slavik.aproximadorfunciones.mmc.datos.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.slavik.aproximadorfunciones.mmc.modelo.Punto
import java.lang.reflect.Type

@ProvidedTypeConverter
class ListaPuntosConverter {
    @TypeConverter
    fun desdeListaPuntos(puntos: MutableList<Punto>): String {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Punto>?>() {}.type
        return gson.toJson(puntos, type) ?: "[]"
    }

    @TypeConverter
    fun aListaPuntos(puntosString: String): MutableList<Punto> {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Punto>?>() {}.type
        return gson.fromJson(puntosString, type) ?: mutableListOf()
    }
}