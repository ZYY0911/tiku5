package com.example.tiku5.bean;

import org.litepal.crud.LitePalSupport;

public class Czjl extends LitePalSupport {
    private String time,xingqi,chongzhiren,chepai,jine;

    public Czjl() {
    }

    public Czjl(String time, String xingqi, String chongzhiren, String chepai, String jine) {
        this.time = time;
        this.xingqi = xingqi;
        this.chongzhiren = chongzhiren;
        this.chepai = chepai;
        this.jine = jine;
    }

    @Override
    public String toString() {
        return "Czjl{" +
                "time='" + time + '\'' +
                ", xingqi='" + xingqi + '\'' +
                ", chongzhiren='" + chongzhiren + '\'' +
                ", chepai='" + chepai + '\'' +
                ", jine='" + jine + '\'' +
                '}';
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getXingqi() {
        return xingqi;
    }

    public void setXingqi(String xingqi) {
        this.xingqi = xingqi;
    }

    public String getChongzhiren() {
        return chongzhiren;
    }

    public void setChongzhiren(String chongzhiren) {
        this.chongzhiren = chongzhiren;
    }

    public String getChepai() {
        return chepai;
    }

    public void setChepai(String chepai) {
        this.chepai = chepai;
    }

    public String getJine() {
        return jine;
    }

    public void setJine(String jine) {
        this.jine = jine;
    }
}
