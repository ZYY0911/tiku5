package com.example.tiku5.bean;

public class Cxjg {
    private String message;

    public Cxjg(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Cxjg{" +
                "message='" + message + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
