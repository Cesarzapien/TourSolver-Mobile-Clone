package com.cesar.toursolvermobile2.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class LastKnownPosition implements Parcelable {
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


    protected LastKnownPosition(Parcel in) {
        id = in.readString();
        date = in.readLong();
        lon = in.readDouble();
        lat = in.readDouble();
        speed = in.readDouble();
        gpsStatus = in.readString();
        batteryLevel = in.readInt();
        accuracy = in.readDouble();
        privateLife = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeLong(date);
        dest.writeDouble(lon);
        dest.writeDouble(lat);
        dest.writeDouble(speed);
        dest.writeString(gpsStatus);
        dest.writeInt(batteryLevel);
        dest.writeDouble(accuracy);
        dest.writeByte((byte) (privateLife ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LastKnownPosition> CREATOR = new Creator<LastKnownPosition>() {
        @Override
        public LastKnownPosition createFromParcel(Parcel in) {
            return new LastKnownPosition(in);
        }

        @Override
        public LastKnownPosition[] newArray(int size) {
            return new LastKnownPosition[size];
        }
    };

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
