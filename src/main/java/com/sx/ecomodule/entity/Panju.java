package com.sx.ecomodule.entity;

import java.math.BigDecimal;

public class Panju {
    private String uuid;

    private Short id;

    private String name;

    private String county;

    private String rural;

    private String village;

    private Short displaceD;

    private Short displaceU;

    private BigDecimal rateD;

    private BigDecimal rateU;

    private Short rainD;

    private Short rainU;

    private BigDecimal rainfallInten4;

    private BigDecimal rainfallInten5;

    private Short warnLevel;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    public String getRural() {
        return rural;
    }

    public void setRural(String rural) {
        this.rural = rural == null ? null : rural.trim();
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village == null ? null : village.trim();
    }

    public Short getDisplaceD() {
        return displaceD;
    }

    public void setDisplaceD(Short displaceD) {
        this.displaceD = displaceD;
    }

    public Short getDisplaceU() {
        return displaceU;
    }

    public void setDisplaceU(Short displaceU) {
        this.displaceU = displaceU;
    }

    public BigDecimal getRateD() {
        return rateD;
    }

    public void setRateD(BigDecimal rateD) {
        this.rateD = rateD;
    }

    public BigDecimal getRateU() {
        return rateU;
    }

    public void setRateU(BigDecimal rateU) {
        this.rateU = rateU;
    }

    public Short getRainD() {
        return rainD;
    }

    public void setRainD(Short rainD) {
        this.rainD = rainD;
    }

    public Short getRainU() {
        return rainU;
    }

    public void setRainU(Short rainU) {
        this.rainU = rainU;
    }

    public BigDecimal getRainfallInten4() {
        return rainfallInten4;
    }

    public void setRainfallInten4(BigDecimal rainfallInten4) {
        this.rainfallInten4 = rainfallInten4;
    }

    public BigDecimal getRainfallInten5() {
        return rainfallInten5;
    }

    public void setRainfallInten5(BigDecimal rainfallInten5) {
        this.rainfallInten5 = rainfallInten5;
    }

    public Short getWarnLevel() {
        return warnLevel;
    }

    public void setWarnLevel(Short warnLevel) {
        this.warnLevel = warnLevel;
    }
}