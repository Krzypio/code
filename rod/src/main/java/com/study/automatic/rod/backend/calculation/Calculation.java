package com.study.automatic.rod.backend.calculation;

public class Calculation {
    private double x, x0, xAlpha = -1000;
    private double y, y0, yAlpha = -1000;
    private double t, t0 = -1000;
    private double z, l, l0=-1000;

    public Calculation(double x0, double xAlpha, double y0, double yAlpha, double z, double t0) {
        this.x0=x0;
        this.x=x0;
        this.xAlpha=xAlpha;
        this.y0=y0;
        this.y=y0;
        this.yAlpha=yAlpha;
        this.z=z;
        this.t0=t0;
        this.t=t0;

        calculateL0();
        calculateL();
    }

    private void calculateX(){
        x=x0*(1+xAlpha+t-t0);
    }

    private void calculateY(){
        y=y0*(1+yAlpha+t-t0);
    }

    private void calculateL(){
        l=x+y+z;
    }

    private void calculateL0(){
        l0=x0+y0+z;
    }

    public void setT(double t) {
        this.t = t;

        calculateX();
        calculateY();
        calculateL();
    }

    public double getX() {
        return x;
    }

    public double getX0() {
        return x0;
    }

    public double getxAlpha() {
        return xAlpha;
    }

    public double getY() {
        return y;
    }

    public double getY0() {
        return y0;
    }

    public double getyAlpha() {
        return yAlpha;
    }

    public double getT() {
        return t;
    }

    public double getT0() {
        return t0;
    }

    public double getZ() {
        return z;
    }

    public double getL() {
        return l;
    }

    public double getL0() {
        return l0;
    }
}
