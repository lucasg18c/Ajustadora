package com.slavik.aproximadorfunciones.legacy.modelo_legacy.funciones;

import com.slavik.aproximadorfunciones.legacy.modelo_legacy.Punto;

public class Parabola extends Funcion {
    public Parabola() {
    }

    public Parabola(Punto[] puntos) {
        super(puntos);
    }

    @Override
    public double[][] generarMatriz() {
        double  x = x(),
                x2 = x2(),
                x3 = x3();

        return new double[][] {
                {x4(), x3, x2, x2y()},
                {x3, x2, x, xy()},
                {x2, x, puntos.length, y()}
        } ;
    }

    @Override
    public String asString() {
        StringBuilder sb = new StringBuilder("f(x) =");

        double  a = solucion[0],
                b = solucion[1],
                c = solucion[2];

        String  as = formatNumber(a),
                bs = formatNumber(b),
                cs = formatNumber(c);

        boolean tieneAlgo = false;

        if (!as.equals("0")){
            tieneAlgo = true;
            if (a < 0) sb.append(" -");
            else sb.append(" ");
            if (!as.equals("1")) sb.append(as);
            sb.append("X<sup>2</sup>");
        }
        if (!bs.equals("0")){
            if (tieneAlgo && b > 0) sb.append(" + ");
            if (b < 0) sb.append(" - ");
            if (!bs.equals("1")) sb.append(bs);
            sb.append("X");
            tieneAlgo = true;
        }
        if (!cs.equals("0")){
            if (tieneAlgo && c > 0) sb.append(" + ");
            if (c < 0) sb.append(" - ");
            else if (!tieneAlgo) sb.append(" ");
            sb.append(formatNumber(c, true));
            tieneAlgo = true;
        }
        if (! tieneAlgo) sb.append("0");

        return sb.toString();
    }

    @Override
    public double valuarEnPunto(double x) {
        return solucion[0] * x * x + solucion[1] * x + solucion[2];
    }

    private double x3(){
        double suma = 0;
        for (Punto p : puntos) suma += (p.x * p.x * p.x);
        return suma;
    }

    private double x4(){
        double suma = 0;
        for (Punto p : puntos) suma += (p.x * p.x * p.x * p.x);
        return suma;
    }

    private double x2y(){
        double suma = 0;
        for (Punto p : puntos) suma += (p.x * p.x * p.y);
        return suma;
    }
}
