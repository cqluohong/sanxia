package com.sx.ecomodule.model;

public class ZaihaidianAttribute {
    private String id;
    private String name;
    private String county;
    private String rural;
    private String village;
    private Double x;
    private Double y;
    private String time;

    @Override
    public String toString() {
        return "ZaihaidianAttribute{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", county='" + county + '\'' +
                ", rural='" + rural + '\'' +
                ", village='" + village + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", time='" + time + '\'' +
                '}';
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getCounty() {
        return county;
    }

    public String getRural() {
        return rural;
    }

    public String getVillage() {
        return village;
    }

    public Double getX() {
        return x;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setRural(String rural) {
        this.rural = rural;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getY() {
        return y;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
