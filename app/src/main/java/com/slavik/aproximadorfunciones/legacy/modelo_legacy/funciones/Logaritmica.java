package com.slavik.aproximadorfunciones.legacy.modelo_legacy.funciones;

import com.slavik.aproximadorfunciones.legacy.modelo_legacy.Punto;

public class Logaritmica extends Funcion {

    public Logaritmica() {
    }

    public Logaritmica(Punto[] puntos) {
        super(puntos);
    }

    double despHorizontal = 0;

    @Override
    public double[][] generarMatriz() {
        calcularDespHorizontal();

        double  ln = ln();

        return new double[][] {
                {ln2(), ln, yln()},
                {ln, puntos.length, y()}
        };
    }

    @Override
    public String asString() {
        StringBuilder sb = new StringBuilder("f(x) =");

        double  a = solucion[0],
                b = solucion[1];

        String  as = formatNumber(a),
                bs = formatNumber(b);

        boolean tieneAlgo = false;

        if (!as.equals("0")){
            tieneAlgo = true;
            if (a < 0) sb.append(" -");
            else sb.append(" ");
            if (!as.equals("1")) sb.append(as);
            sb.append(" ln(x");
            if (despHorizontal != 0) sb.append(" + ").append(formatNumber(despHorizontal));
            sb.append(")");
        }
        if (!bs.equals("0")){
            if (tieneAlgo && b > 0) sb.append(" + ");
            if (b < 0) sb.append(" - ");
            sb.append(formatNumber(b, true));
            tieneAlgo = true;
        }
        if (! tieneAlgo) sb.append("0");

        return sb.toString();    }

    @Override
    public double valuarEnPunto(double x) {
        return solucion[0] * Math.log(x + despHorizontal) + solucion[1];
    }

    private double ln() {
        double suma = 0;
        for (Punto p : puntos) suma += Math.log(p.x + despHorizontal);
        return suma;
    }

    private double yln() {
        double suma = 0;
        for (Punto p : puntos) suma += (p.y * Math.log(p.x + despHorizontal));
        return suma;
    }

    private double ln2() {
        double suma = 0;
        for (Punto p : puntos) suma += (Math.pow( Math.log(p.x + despHorizontal), 2));
        return suma;
    }

    private void calcularDespHorizontal() {
        double menorX = puntos[0].x;
        for (int i = 1; i < puntos.length; i++){
            if (puntos[i].x < menorX){
                menorX = puntos[i].x;
            }
        }
        if (menorX <= 0) despHorizontal = Math.abs(menorX) + 1;
        else despHorizontal = 0;
    }
}
