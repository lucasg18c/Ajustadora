package com.slavik.aproximadorfunciones.mmc.ui.navegacion

sealed class Destino(val ruta: String) {
    object Inicio : Destino(ruta = "inicio_screen")
    object Calculadora : Destino(ruta = "calculadora_screen")
    object EditarModelo : Destino(ruta = "editar_modelo_screen")
    object Formas : Destino(ruta = "formas_screen")
    object Resultado : Destino(ruta = "resultado_screen")
    object Configuracion : Destino(ruta = "configuracion_screen")
}
