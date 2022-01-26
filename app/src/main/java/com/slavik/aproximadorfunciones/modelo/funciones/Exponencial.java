package com.slavik.aproximadorfunciones.modelo.funciones;

import com.slavik.aproximadorfunciones.modelo.Punto;

public class Exponencial extends Funcion {
    public Exponencial() {
    }

    public Exponencial(Punto[] puntos) {
        super(puntos);
    }

    @Override
    public double[][] generarMatriz() {
        double ex = ex();

        return new double[][] {
                {e2x(), ex, exy()},
                {ex, puntos.length, y()}
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
            sb.append("<i> e</i><sup> x</sup>");
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

        return sb.toString();    }

    @Override
    public double valuarEnPunto(double x) {
        return solucion[0] * Math.pow(Math.E, x) + solucion[1];
    }

    private double exy() {
        double suma = 0;
        for (Punto p : puntos) suma += (p.y * Math.pow(Math.E, p.x));
        return suma;
    }

    private double e2x() {
        double suma = 0;
        for (Punto p : puntos) suma += (Math.pow(Math.E, 2 * p.x));
        return suma;
    }

    private double ex() {
        double suma = 0;
        for (Punto p : puntos) suma += (Math.pow(Math.E, p.x));
        return suma;
    }
}
