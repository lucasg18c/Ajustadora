package com.slavik.aproximadorfunciones.modelo.funciones;

import com.slavik.aproximadorfunciones.modelo.Punto;

public class Lineal extends Funcion {

    public Lineal(){

    }

    public Lineal(Punto[] puntos){
        super(puntos);
    }

    @Override
    public double[][] generarMatriz() {
        double x = x();
        return new double[][]{
                {x2(), x, xy()},
                {x, puntos.length, y()}
        };
    }

    @Override
    public String asString() {
        StringBuilder sb = new StringBuilder("f(x) = ");
        double  a = solucion[0],
                b = solucion[1];

        String  as = formatNumber(a),
                bs = formatNumber(b);

        boolean tieneA = false;

        if (!as.equals("0")){
            tieneA = true;
            if (a < 0) sb.append("-");
            if (!as.equals("1")) sb.append(as);
            sb.append("X");
        }
        if (!bs.equals("0")){
            if (tieneA){
                sb.append(" ");
                if (b > 0) sb.append("+ ");
            }
            if (b < 0) sb.append("- ");
            sb.append(formatNumber(b, true));
        }
        else if (! tieneA) sb.append("0");

        return sb.toString();
    }

    @Override
    public double valuarEnPunto(double x) {
        return solucion[0] * x + solucion[1];
    }
}
