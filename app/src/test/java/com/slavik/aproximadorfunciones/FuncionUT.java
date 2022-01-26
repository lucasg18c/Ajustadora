package com.slavik.aproximadorfunciones;

import com.slavik.aproximadorfunciones.modelo.funciones.Exponencial;
import com.slavik.aproximadorfunciones.modelo.funciones.Funcion;
import com.slavik.aproximadorfunciones.modelo.funciones.Lineal;
import com.slavik.aproximadorfunciones.modelo.funciones.Logaritmica;
import com.slavik.aproximadorfunciones.modelo.funciones.Parabola;
import com.slavik.aproximadorfunciones.modelo.Punto;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class FuncionUT {
    private static Punto[] puntos;
    Funcion f;

    @BeforeClass
    public static void antes(){
        puntos = new Punto[]{
                new Punto(1, 1),
                new Punto(2, 7),
                new Punto(4, 10),
                new Punto(6, 2),
        };
    }

    @Test
    public void LinealToString() {
        f = new Lineal(puntos);
        assertEquals("f(x) = 0,5X", f.toString());
        assertEquals(0, f.desviacion(), 0.1);

    }

    @Test
    public void ParabolaToString(){
        f = new Parabola(puntos);
        assertEquals("f(x) = 0,5X", f.toString());
        assertEquals(0, f.desviacion(), 0.1);

    }

    @Test
    public void ExponencialToString() {
        f = new Exponencial(puntos);
        assertEquals("f(x) = 0,5X", f.toString());
        assertEquals(0, f.desviacion(), 0.1);
    }

    @Test
    public void LogaritmicaToString(){
        f = new Logaritmica(puntos);
        assertEquals("f(x) = 0,5X", f.toString());
        assertEquals(0, f.desviacion(), 0.1);

    }

}
