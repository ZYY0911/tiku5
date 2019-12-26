package com.example.tiku5.bean;

/**
 * Create by 张瀛煜 on 2019-12-26
 */
public class SSHJ {

    /**
     * temperature : 33
     * humidity : 18
     * illumination : 3197
     * co2 : 6278
     * pm25 : 45
     * RESULT : S
     */

    private int temperature;
    private int illumination;
    private int pm25;
    private String RESULT;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getIllumination() {
        return illumination;
    }

    public void setIllumination(int illumination) {
        this.illumination = illumination;
    }

    public int getPm25() {
        return pm25;
    }

    public void setPm25(int pm25) {
        this.pm25 = pm25;
    }

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }
}
