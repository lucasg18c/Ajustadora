package com.slavik.aproximadorfunciones.mmc.modelo

import com.slavik.aproximadorfunciones.mmc.util.Formato

data class Punto(
    var x : Float,
    var y : Float
){
    override fun toString(): String {
        return "(${Formato.decimal(x)}; ${Formato.decimal(y)})"
    }
}