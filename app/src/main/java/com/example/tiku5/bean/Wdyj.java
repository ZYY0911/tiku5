package com.example.tiku5.bean;

public class Wdyj {
    private String bt,yj,sj;

    public Wdyj(String bt, String yj, String sj) {
        this.bt = bt;
        this.yj = yj;
        this.sj = sj;
    }

    @Override
    public String toString() {
        return "Wdyj{" +
                "bt='" + bt + '\'' +
                ", yj='" + yj + '\'' +
                ", sj='" + sj + '\'' +
                '}';
    }

    public String getBt() {
        return bt;
    }

    public void setBt(String bt) {
        this.bt = bt;
    }

    public String getYj() {
        return yj;
    }

    public void setYj(String yj) {
        this.yj = yj;
    }

    public String getSj() {
        return sj;
    }

    public void setSj(String sj) {
        this.sj = sj;
    }
}
