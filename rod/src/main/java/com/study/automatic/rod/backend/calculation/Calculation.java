package com.study.automatic.rod.backend.calculation;

public class Calculation {
    private double x;

    public void setX(double x) {
        this.x = x;
    }

    public void setXi(double xi) {
        this.xi = xi;
    }

    public void setxAlpha(double xAlpha) {
        this.xAlpha = xAlpha;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setYi(double yi) {
        this.yi = yi;
    }

    public void setyAlpha(double yAlpha) {
        this.yAlpha = yAlpha;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setLicznik(double licznik) {
        this.licznik = licznik;
    }

    public void setPsi(double psi) {
        this.psi = psi;
    }

    public void setL(double l) {
        this.l = l;
    }

    private double xi;
    private double xAlpha ;
    private double y, yi, yAlpha ,z;
    private double T; //temperatura z suwaka
    private double t; //temperatura poprzedniego kroku
private double  licznik=0;



    public double getTi() {
        return ti;
    }

    public void setTi(double ti) {
        this.ti = ti;
        calculateLi();
    }

    private double ti;
    private double psi;

    public void setLpoprz(double lpoprz) {
        Lpoprz = lpoprz;
    }

    private double Lpoprz;
    private double l;



    public double getLpoprz() {
        return Lpoprz;
    }

    public double getl() {
        return l;
    }



    public double getXi() {
        return xi;
    }



    public double getPsi() {
        return psi;
    }

    public Calculation(double x,double y, double t,double z,double xAlpha,double yAlpha ) {
        this.x=x;
        this.y=y;
        this.z=z;
        this.t=t;
        this.xAlpha=xAlpha;
        this.yAlpha=yAlpha;
        this.Lpoprz=Lpoprz;
      //  calculateLi();

    }



    private void calculateLi(){//finalny krok. wynik do glownego wykresu L w kroku wybrania tej temperatury
    setLpoprz(l);
        if(licznik ==0){
            l=x+y+z;
            licznik++;
            setLpoprz(l);
            setX(x);
            setY(y);
            setZ(z);

        }
        else{
            double delta=ti-t;//dla 1 obliczenia t0 = stałe 20 potem to wynik z poprzedniego kroku
            xi=x*(1+xAlpha*delta);
            yi=y*(1+yAlpha*delta);
            psi=xi+yi-x-y;

            setX(xi);
            setY(yi);
            setT(ti);
            l=Lpoprz+psi;//w pierwszym kroku l0 ma byc rowne Lstart, potem to kolejne wyniki
        }

    }

    public void setTpoprz(double t) {
        this.t = t;


        calculateLi();
    }


    public void setT(double t) {
        this.t = t;



    }

    public double getX() {
        return x;
    }



    public double getxAlpha() {
        return xAlpha;
    }

    public double getY() {
        return y;
    }



    public double getyAlpha() {
        return yAlpha;
    }

    public double getT() {
        return t;
    }



    public double getL() {
        return l;
    }


}
