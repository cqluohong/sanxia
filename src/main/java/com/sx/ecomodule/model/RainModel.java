package com.sx.ecomodule.model;

public class RainModel {
    private String id;
    private String name;
    private Double x;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private Double y;
    private Double rainValue;
    private String time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private Double w;

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getRainValue() {
        return rainValue;
    }

    public void setRainValue(Double rainValue) {
        this.rainValue = rainValue;
    }

    public Double getW() {
        return w;
    }

    public void setW(Double w) {
        this.w = w;
    }
}
