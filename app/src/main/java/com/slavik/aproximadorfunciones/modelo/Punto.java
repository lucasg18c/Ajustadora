package com.slavik.aproximadorfunciones.modelo;

public class Punto {
    public double x, y;
    public boolean visible;

    public Punto(double x, double y){
        this.x = x;
        this.y = y;
        visible = true;
    }

    public Punto(double x, double y, boolean visible) {
        this.x = x;
        this.y = y;
        this.visible = visible;
    }
}
