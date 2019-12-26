package com.example.tiku5.bean;

/**
 * Create by 张瀛煜 on 2019-12-26
 */
public class HLD {

    /**
     * id : 1
     * number : 1
     * red : 15
     * yellow : 15
     * green : 15
     */

    private int id;
    private int red;
    private int yellow;
    private int green;
    private boolean xz;

    public boolean isXz() {
        return xz;
    }

    public void setXz(boolean xz) {
        this.xz = xz;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getYellow() {
        return yellow;
    }

    public void setYellow(int yellow) {
        this.yellow = yellow;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }
}
