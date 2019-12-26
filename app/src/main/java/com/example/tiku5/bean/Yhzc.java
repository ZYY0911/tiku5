package com.example.tiku5.bean;

import org.litepal.crud.LitePalSupport;

public class Yhzc extends LitePalSupport {
    private String yhm,yx,mm,qrmm;

    public Yhzc() {
    }

    public Yhzc(String yhm, String yx, String mm, String qrmm) {
        this.yhm = yhm;
        this.yx = yx;
        this.mm = mm;
        this.qrmm = qrmm;
    }

    @Override
    public String toString() {
        return "Yhzc{" +
                "yhm='" + yhm + '\'' +
                ", yx='" + yx + '\'' +
                ", mm='" + mm + '\'' +
                ", qrmm='" + qrmm + '\'' +
                '}';
    }

    public String getYhm() {
        return yhm;
    }

    public void setYhm(String yhm) {
        this.yhm = yhm;
    }

    public String getYx() {
        return yx;
    }

    public void setYx(String yx) {
        this.yx = yx;
    }

    public String getMm() {
        return mm;
    }

    public void setMm(String mm) {
        this.mm = mm;
    }

    public String getQrmm() {
        return qrmm;
    }

    public void setQrmm(String qrmm) {
        this.qrmm = qrmm;
    }
}
