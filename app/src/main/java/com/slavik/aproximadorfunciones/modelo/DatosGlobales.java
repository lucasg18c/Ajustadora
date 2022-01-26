package com.slavik.aproximadorfunciones.modelo;

import com.slavik.aproximadorfunciones.modelo.funciones.AutoFuncion;
import com.slavik.aproximadorfunciones.modelo.funciones.Exponencial;
import com.slavik.aproximadorfunciones.modelo.funciones.Funcion;
import com.slavik.aproximadorfunciones.modelo.funciones.Lineal;
import com.slavik.aproximadorfunciones.modelo.funciones.Logaritmica;
import com.slavik.aproximadorfunciones.modelo.funciones.Parabola;

public class DatosGlobales {

    public static Funcion funcion;
    public static Punto[] puntos;

    public static void setForma(String tag) {

        switch (tag) {
            case "recta":
                funcion = new Lineal();
                break;

            case "parabola":
                funcion = new Parabola();
                break;

            case "exponencial":
                funcion = new Exponencial();
                break;
            case "logaritmica":
                funcion = new Logaritmica();
                break;
            case "automatic":
                funcion = new AutoFuncion();
                break;
        }
    }
}
