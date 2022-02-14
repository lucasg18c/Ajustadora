package com.slavik.aproximadorfunciones.mmc.util

import java.text.DecimalFormat
import java.util.*
import kotlin.math.abs

object Formato {

    fun fecha(fecha: Calendar) : String {
        var dia = fecha.get(Calendar.DAY_OF_MONTH).toString()
        var mes = (fecha.get(Calendar.MONTH) + 1).toString()
        val year = fecha.get(Calendar.YEAR).toString().substring(2, 4)

        if (dia.length == 1) dia = "0$dia"
        if (mes.length == 1) mes = "0$mes"

        return "${dia}/${mes}/${year}"
    }

    fun decimal(numero: Float) : String {
        return DecimalFormat("#.##").format(numero)
    }

    fun decimal(numero: Double) : String {
        if (numero == 0.0) return "0"
        return DecimalFormat("#.##").format(numero)
    }

    fun aDecimal(numero: String) : Double {
        return try {
            numero.toDouble()
        } catch (e: NumberFormatException) {
            Double.NaN
        }
    }

    fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return kotlin.math.round(this * multiplier) / multiplier
    }

    fun coeficiente(coeficiente: Double): String {
        return decimal(abs(coeficiente))
    }
}