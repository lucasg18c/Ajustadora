package com.slavik.aproximadorfunciones.modelo.funciones;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.slavik.aproximadorfunciones.modelo.Punto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;


public abstract class Funcion {

    private Lineal auxiliar;
    double[] solucion;

    public Punto[] getPuntos() {
        return puntos;
    }

    Punto[] puntos;

    public Funcion(){

    }

    public Funcion(Punto[] puntos){
        this.puntos = puntos;
        calcularSolucion();
    }

    public void calcularSolucion(){
        auxiliar = null;
        validarPuntos();
        ordenar();
        try {
            double[][] matriz = generarMatriz();
            round(matriz, 3);
            resolverMatriz(matriz);
        }
        catch (Exception e){
            auxiliar = new Lineal(puntos);
            solucion = auxiliar.solucion;
        }
    }

    public final void calcularSolucion(Punto[] puntos){
        this.puntos = puntos;
        calcularSolucion();
    }

    protected abstract double[][] generarMatriz();
    protected abstract double valuarEnPunto(double x);
    protected abstract String asString();

    public double valuar(double x){
        if (auxiliar != null) return auxiliar.valuar(x);
        return valuarEnPunto(x);
    }

    @Override
    public String toString(){
        if (auxiliar != null) return auxiliar.asString();
        if (solucion == null) return "No resuleta aún";
        return asString();
    }

    public LineGraphSeries<DataPoint> generarGrafica(){
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
        double x = -150, y;

        for (int i = 0; i < 1500; i++){
            x += 0.2;
            y = valuar(x);
            series.appendData(new DataPoint(x, y), false, 1500);
        }
        return series;
    }

    private void resolverMatriz(double[][] matriz) {
        reducirMatriz(matriz);
        if (tieneSolucion(matriz)) resolverSistemaEcuaciones(matriz);
        else{
            auxiliar = new Lineal(puntos);
            solucion = auxiliar.solucion;
        }
    }

    private boolean tieneSolucion(double[][] matriz) {
        for (int i = 0; i < matriz.length; i++){
            if (matriz[i][i] == 0) return false;
        }
        return true;
    }

    private void resolverSistemaEcuaciones(double[][] matriz) {
        int n = matriz.length;
        int m = n - 1;
        solucion = new double[n];
        solucion[m] = matriz[m][n] / matriz[m][m];
        for (int fila = n - 2; fila >= 0; fila--){
            solucion[fila] = matriz[fila][n];
            for (int col = n - 1; col > fila; col--){
                solucion[fila] -= matriz[fila][col] * solucion[col];
            }
            solucion[fila] /= matriz[fila][fila];
            solucion[fila] = round(solucion[fila], 3);
        }
    }

    private void reducirMatriz(double[][] matriz) {
        int n = matriz.length;
        double k;

        for (int col = 0; col < n; col++){
            for (int fila = 0; fila < n; fila++){
                if (fila > col){
                    if (matriz[col][col] == 0) k = 0;
                    else k = round(-matriz[fila][col] / matriz[col][col], 10);

                    for (int c = 0; c < n + 1; c++){
                        matriz[fila][c] += round(matriz[col][c] * k, 5);
                        matriz[fila][c] = round(matriz[fila][c], 5);
                    }
                }
            }
        }
    }

    protected final double y() {
        double suma = 0;
        for (Punto p : puntos) suma +=(p.y);
        return suma;
    }

    protected final double x() {
        double suma = 0;
        for (Punto p : puntos) suma += (p.x);
        return suma;
    }

    protected final double xy() {
        double suma = 0;
        for (Punto p : puntos) suma += (p.x * p.y);
        return suma;
    }

    protected final double x2() {
        double suma = 0;
        for (Punto p : puntos) suma += (p.x * p.x);
        return suma;
    }

    public final String formatNumber(double number){
        return formatNumber(number, false);
    }

    public final String formatNumber(double number, boolean allowZero){

        DecimalFormat df = new DecimalFormat("0.###");

        String n = String.valueOf(Math.abs(round(number, 3)));
        n = df.format(Double.parseDouble(n));

        int i = n.indexOf(',');

        if (i == -1) return n;
        String decimalString = n.substring(i+1);
        int cont = 1;
        for (char c : decimalString.toCharArray()){
            if (c != '0') return n;
            if (cont++ == 3) break;
        }
        decimalString = n.substring(0, i);
        if (!decimalString.equals("0")) return decimalString;

        if (n.equals("0") && !allowZero) return String.valueOf(Math.abs(number));
        return n;
    }

    public final double desviacion(){
        double suma = 0, fx, y;

        for (Punto punto : puntos) {
            fx = valuar(punto.x);
            y = punto.y;

            suma += Math.pow(fx - y, 2);
        }
        return suma;
    }

    private void validarPuntos() {
        int n = puntos.length;
        if (n == 0){
            throw new RuntimeException("No hay puntos válidos");
        }

        double x = puntos[0].x;
        for (int i = 1; i < puntos.length; i++){
            if (puntos[i].x != x) return;
        }

        float y;

        Punto[] temp = new Punto[n + 1];
        System.arraycopy(puntos, 0, temp, 0, n);

        x = puntos[0].x + 1;
        y = 0;

        for (Punto p : puntos) y += p.y;
        y /= n;

        temp[n] = new Punto(x, y, false);
        puntos = temp;
    }

    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private void round(double[][] matriz, int places){

        for (int i = 0; i < matriz.length; i++){
            for (int j = 0; j < matriz[i].length; j++){
                matriz[i][j] = round(matriz[i][j], places);
            }
        }
    }

    private void ordenar(){
        for (int i = 0; i < puntos.length - 1; i++){
            for (int j = i; j < puntos.length; j++){
                if (puntos[i].x > puntos[j].x){
                    Punto temp = puntos[i];
                    puntos[i] = puntos[j];
                    puntos[j] = temp;
                }
            }
        }
    }

    public void setPuntos(Punto[] puntos) {
        this.puntos = puntos;
    }

    public void addPuntoSoporte(){
        int n = puntos.length;
        if (n == 2){
            Punto[] temp = new Punto[n + 1];
            System.arraycopy(puntos, 0, temp, 0, n);

            double mayorX = 0, mayorY = 0;
            for (Punto p : puntos){
                if (p.x > mayorX) mayorX = p.x;
                if (p.y > mayorY) mayorY = p.y;
            }
            temp[n] = new Punto(mayorX + 1, mayorY + 2, false);
            puntos = temp;
        }
    }
}
