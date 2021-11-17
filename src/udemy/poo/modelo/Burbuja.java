
package udemy.poo.modelo;

import java.util.Random;

/**
 *
 * @author RONALD
 */
public class Burbuja {
    
    private int xx;
    private int yy;
    private int cx;
    private int cy;
    private int radio;
    private boolean arriba = false;
    private boolean abajo = false;
    private boolean izquierda = false;
    private boolean derecha = false;
    private boolean eliminar = false;
    private String whoDeleted = "Nadie";

    public Burbuja(int xx, int yy, int cx, int cy, int radio) {
        this.xx = xx;
        this.yy = yy;
        this.cx = cx;
        this.cy = cy;
        this.radio = radio;
        
        Random rd = new Random();
        
        if (rd.nextInt() <= 50) {
            izquierda = true;
            arriba = true;
        } else {
            derecha = true;
            abajo = true;
        }
    }

    public int getXx() {
        return xx;
    }

    public void setXx(int xx) {
        this.xx = xx;
    }

    public int getYy() {
        return yy;
    }

    public void setYy(int yy) {
        this.yy = yy;
    }

    public int getCx() {
        return cx;
    }

    public void setCx(int cx) {
        this.cx = cx;
    }

    public int getCy() {
        return cy;
    }

    public void setCy(int cy) {
        this.cy = cy;
    }

    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }

    public boolean isArriba() {
        return arriba;
    }

    public void setArriba(boolean arriba) {
        this.arriba = arriba;
    }

    public boolean isAbajo() {
        return abajo;
    }

    public void setAbajo(boolean abajo) {
        this.abajo = abajo;
    }

    public boolean isIzquierda() {
        return izquierda;
    }

    public void setIzquierda(boolean izquierda) {
        this.izquierda = izquierda;
    }

    public boolean isDerecha() {
        return derecha;
    }

    public void setDerecha(boolean derecha) {
        this.derecha = derecha;
    }

    public boolean isEliminar() {
        return eliminar;
    }

    public void setEliminar(boolean eliminar) {
        this.eliminar = eliminar;
    }

    public String getWhoDeleted() {
        return whoDeleted;
    }

    public void setWhoDeleted(String whoDeleted) {
        this.whoDeleted = whoDeleted;
    }
    
    
}
