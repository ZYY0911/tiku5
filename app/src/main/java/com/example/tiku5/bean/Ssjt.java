package com.example.tiku5.bean;

public class Ssjt {
    private String luhao,luxain;

    public Ssjt(String luhao, String luxain) {
        this.luhao = luhao;
        this.luxain = luxain;
    }

    @Override
    public String toString() {
        return "Ssjt{" +
                "luhao='" + luhao + '\'' +
                ", luxain='" + luxain + '\'' +
                '}';
    }

    public String getLuhao() {
        return luhao;
    }

    public void setLuhao(String luhao) {
        this.luhao = luhao;
    }

    public String getLuxain() {
        return luxain;
    }

    public void setLuxain(String luxain) {
        this.luxain = luxain;
    }
}
