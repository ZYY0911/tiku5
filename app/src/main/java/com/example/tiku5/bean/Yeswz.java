package com.example.tiku5.bean;

public class Yeswz {
    private String chepai,yuanyin;

    public Yeswz(String chepai, String yuanyin) {
        this.chepai = chepai;
        this.yuanyin = yuanyin;
    }


    @Override
    public String toString() {
        return "Yeswz{" +
                "chepai='" + chepai + '\'' +
                ", yuanyin='" + yuanyin + '\'' +
                '}';
    }

    public String getChepai() {
        return chepai;
    }

    public void setChepai(String chepai) {
        this.chepai = chepai;
    }

    public String getYuanyin() {
        return yuanyin;
    }

    public void setYuanyin(String yuanyin) {
        this.yuanyin = yuanyin;
    }
}
