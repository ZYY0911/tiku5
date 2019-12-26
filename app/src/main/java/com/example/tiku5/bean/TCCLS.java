package com.example.tiku5.bean;

/**
 * Create by 张瀛煜 on 2019-12-26
 */
public class TCCLS {

    /**
     * id : 2
     * number : 3
     * plate : ³A10003
     * entrance : 2014-5-15 05:30:07
     * exit : 2014-6-15 05:30:07
     * price : 3
     */

    private int id;
    private int number;
    private String plate;
    private String entrance;
    private String exit;
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getEntrance() {
        return entrance;
    }

    public void setEntrance(String entrance) {
        this.entrance = entrance;
    }

    public String getExit() {
        return exit;
    }

    public void setExit(String exit) {
        this.exit = exit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
