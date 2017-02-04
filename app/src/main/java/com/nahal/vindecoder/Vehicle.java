package com.nahal.vindecoder;

/**
 * Created by Sukhjinder on 2/4/17.
 */

public class Vehicle {

    String make;
    String model;
    String interiorColor;
    String exteriorColor;
    String baseMSRP;
    String vehicleSize;
    String cityMPG;
    String highwayMPG;

    public Vehicle(String make, String model, String interiorColor, String exteriorColor, String baseMSRP, String vehicleSize, String cityMPG, String highwayMPG) {
        this.make = make;
        this.model = model;
        this.interiorColor = interiorColor;
        this.exteriorColor = exteriorColor;
        this.baseMSRP = baseMSRP;
        this.vehicleSize = vehicleSize;
        this.cityMPG = cityMPG;
        this.highwayMPG = highwayMPG;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getInteriorColor() {
        return interiorColor;
    }

    public void setInteriorColor(String interiorColor) {
        this.interiorColor = interiorColor;
    }

    public String getExteriorColor() {
        return exteriorColor;
    }

    public void setExteriorColor(String exteriorColor) {
        this.exteriorColor = exteriorColor;
    }

    public String getBaseMSRP() {
        return baseMSRP;
    }

    public void setBaseMSRP(String baseMSRP) {
        this.baseMSRP = baseMSRP;
    }

    public String getVehicleSize() {
        return vehicleSize;
    }

    public void setVehicleSize(String vehicleSize) {
        this.vehicleSize = vehicleSize;
    }

    public String getCityMPG() {
        return cityMPG;
    }

    public void setCityMPG(String cityMPG) {
        this.cityMPG = cityMPG;
    }

    public String getHighwayMPG() {
        return highwayMPG;
    }

    public void setHighwayMPG(String highwayMPG) {
        this.highwayMPG = highwayMPG;
    }
}
