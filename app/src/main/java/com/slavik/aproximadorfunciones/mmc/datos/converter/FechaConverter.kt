package com.slavik.aproximadorfunciones.mmc.datos.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.util.*

@ProvidedTypeConverter
class FechaConverter {

    @TypeConverter
    fun desdeCalendar(fecha: Calendar): Long {
        return fecha.timeInMillis
    }

    @TypeConverter
    fun aCalendar(fecha: Long): Calendar {
        return Calendar.getInstance().apply { time = Date(fecha) }
    }
}