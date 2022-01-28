package com.slavik.aproximadorfunciones.mmc.presentacion.navegacion

sealed class Destino(val ruta: String) {
    object Inicio : Destino(ruta = Rutas.INICIO)
    object Calculadora : Destino(ruta = Rutas.CALCULADORA)
    object EditarModelo : Destino(ruta = Rutas.EDITAR_MODELO)
    object Formas : Destino(ruta = Rutas.FORMAS)
    object Resultado : Destino(ruta = Rutas.RESULTADO)
    object Configuracion : Destino(ruta = Rutas.CONFIGURACION)
}
