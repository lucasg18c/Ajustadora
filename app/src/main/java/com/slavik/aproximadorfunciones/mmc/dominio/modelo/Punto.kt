package com.slavik.aproximadorfunciones.mmc.dominio.modelo

import com.slavik.aproximadorfunciones.mmc.util.Formato

data class Punto(
    var x : Double,
    var y : Double,
    var visible: Boolean = true
){
    override fun toString(): String {
        return "(${Formato.decimal(x)}; ${Formato.decimal(y)})"
    }

    constructor(x: Float, y: Float, visible: Boolean = true) : this(x.toDouble(), y.toDouble(), visible)
    constructor(x: Int, y: Int, visible: Boolean = true) : this(x.toDouble(), y.toDouble(), visible)
}