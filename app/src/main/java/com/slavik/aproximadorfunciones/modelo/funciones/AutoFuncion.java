package com.slavik.aproximadorfunciones.modelo.funciones;

import com.slavik.aproximadorfunciones.modelo.Punto;

public class AutoFuncion extends Funcion {

    Funcion funcion;

    public AutoFuncion() {
    }

    public AutoFuncion(Punto[] puntos) {
        super(puntos);
    }

    @Override
    public double[][] generarMatriz() {
        funcion = determinarFuncion();
        return funcion.generarMatriz();
    }

    @Override
    public double valuarEnPunto(double x) {
        return funcion.valuar(x);
    }

    @Override
    public String asString() {
        return funcion.toString();
    }

    private Funcion determinarFuncion() {
        double[] desviaciones = new double[4];

        Funcion f = new Lineal(puntos);
        if (puntos.length == 2) return f;
        desviaciones[0] = f.desviacion();

        f = new Parabola(puntos);
        desviaciones[1] = f.desviacion();

        f = new Exponencial(puntos);
        desviaciones[2] = f.desviacion();

        f = new Logaritmica(puntos);
        desviaciones[3] = f.desviacion();

        double menor = -1;
        int indexMenor = 0;

        for (int i = 0; i < desviaciones.length; i++){
            if ( i == 0 || desviaciones[i] < menor){
                menor = desviaciones[i];
                indexMenor = i;
            }
        }

        switch (indexMenor){
            case 0: return new Lineal(puntos);
            case 1: return new Parabola(puntos);
            case 2: return new Exponencial(puntos);
            case 3: return new Logaritmica(puntos);
        }
        return new Lineal(puntos);
    }
}
