package com.cesar.toursolvermobile2.model;

import com.google.gson.annotations.SerializedName;

public class LastKnownPosition {
    @SerializedName("id")
    private String id;
    @SerializedName("date")
    private long date;
    @SerializedName("lon")
    private double lon;
    @SerializedName("lat")
    private double lat;
    @SerializedName("speed")
    private double speed;
    @SerializedName("gpsStatus")
    private String gpsStatus;
    @SerializedName("batteryLevel")
    private int batteryLevel;
    @SerializedName("accuracy")
    private double accuracy;
    @SerializedName("privateLife")
    private boolean privateLife;
    // Otros campos y métodos

    // Getters y setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getGpsStatus() {
        return gpsStatus;
    }

    public void setGpsStatus(String gpsStatus) {
        this.gpsStatus = gpsStatus;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public boolean isPrivateLife() {
        return privateLife;
    }

    public void setPrivateLife(boolean privateLife) {
        this.privateLife = privateLife;
    }

    @Override
    public String toString() {
        return "LastKnownPosition{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", lon=" + lon +
                ", lat=" + lat +
                ", speed=" + speed +
                ", gpsStatus='" + gpsStatus + '\'' +
                ", batteryLevel=" + batteryLevel +
                ", accuracy=" + accuracy +
                ", privateLife=" + privateLife +
                '}';
    }
}
