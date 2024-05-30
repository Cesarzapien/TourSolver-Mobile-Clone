package com.cesar.toursolvermobile2.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PlannedOrder implements Parcelable {
    @SerializedName("resourceId")
    private String resourceId;
    @SerializedName("dayId")
    private String dayId;
    @SerializedName("stopId")
    private String stopId;
    @SerializedName("stopPosition")
    private int stopPosition;
    @SerializedName("stopY")
    private double stopY;
    @SerializedName("stopX")
    private double stopX;
    @SerializedName("stopType")
    private int stopType;
    @SerializedName("stopDriveTime")
    private String stopDriveTime;
    @SerializedName("stopStartTime")
    private String stopStartTime;
    @SerializedName("stopDuration")
    private String stopDuration;
    @SerializedName("stopStatus")
    private int stopStatus;
    @SerializedName("stopDriveDistance")
    private int stopDriveDistance;
    @SerializedName("stopElapsedDistance")
    private int stopElapsedDistance;
    @SerializedName("placeName")
    private String placeName;
    @SerializedName("placeAddress")
    private String placeAddress;

    @SerializedName("aggregationInfo")
    private String aggregationInfo;

    @SerializedName("walkGoupRootId")
    private String walkGoupRootId;

    @SerializedName("stopDriveSpeed")
    private double stopDriveSpeed;

    // Getters y setters


    protected PlannedOrder(Parcel in) {
        resourceId = in.readString();
        dayId = in.readString();
        stopId = in.readString();
        stopPosition = in.readInt();
        stopY = in.readDouble();
        stopX = in.readDouble();
        stopType = in.readInt();
        stopDriveTime = in.readString();
        stopStartTime = in.readString();
        stopDuration = in.readString();
        stopStatus = in.readInt();
        stopDriveDistance = in.readInt();
        stopElapsedDistance = in.readInt();
        placeName = in.readString();
        placeAddress = in.readString();
        aggregationInfo = in.readString();
        walkGoupRootId = in.readString();
        stopDriveSpeed = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(resourceId);
        dest.writeString(dayId);
        dest.writeString(stopId);
        dest.writeInt(stopPosition);
        dest.writeDouble(stopY);
        dest.writeDouble(stopX);
        dest.writeInt(stopType);
        dest.writeString(stopDriveTime);
        dest.writeString(stopStartTime);
        dest.writeString(stopDuration);
        dest.writeInt(stopStatus);
        dest.writeInt(stopDriveDistance);
        dest.writeInt(stopElapsedDistance);
        dest.writeString(placeName);
        dest.writeString(placeAddress);
        dest.writeString(aggregationInfo);
        dest.writeString(walkGoupRootId);
        dest.writeDouble(stopDriveSpeed);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PlannedOrder> CREATOR = new Creator<PlannedOrder>() {
        @Override
        public PlannedOrder createFromParcel(Parcel in) {
            return new PlannedOrder(in);
        }

        @Override
        public PlannedOrder[] newArray(int size) {
            return new PlannedOrder[size];
        }
    };

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getDayId() {
        return dayId;
    }

    public void setDayId(String dayId) {
        this.dayId = dayId;
    }

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public int getStopPosition() {
        return stopPosition;
    }

    public void setStopPosition(int stopPosition) {
        this.stopPosition = stopPosition;
    }

    public double getStopY() {
        return stopY;
    }

    public void setStopY(double stopY) {
        this.stopY = stopY;
    }

    public double getStopX() {
        return stopX;
    }

    public void setStopX(double stopX) {
        this.stopX = stopX;
    }

    public int getStopType() {
        return stopType;
    }

    public void setStopType(int stopType) {
        this.stopType = stopType;
    }

    public String getStopDriveTime() {
        return stopDriveTime;
    }

    public void setStopDriveTime(String stopDriveTime) {
        this.stopDriveTime = stopDriveTime;
    }

    public String getStopStartTime() {
        return stopStartTime;
    }

    public void setStopStartTime(String stopStartTime) {
        this.stopStartTime = stopStartTime;
    }

    public String getStopDuration() {
        return stopDuration;
    }

    public void setStopDuration(String stopDuration) {
        this.stopDuration = stopDuration;
    }

    public int getStopStatus() {
        return stopStatus;
    }

    public void setStopStatus(int stopStatus) {
        this.stopStatus = stopStatus;
    }

    public int getStopDriveDistance() {
        return stopDriveDistance;
    }

    public void setStopDriveDistance(int stopDriveDistance) {
        this.stopDriveDistance = stopDriveDistance;
    }

    public int getStopElapsedDistance() {
        return stopElapsedDistance;
    }

    public void setStopElapsedDistance(int stopElapsedDistance) {
        this.stopElapsedDistance = stopElapsedDistance;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceAddress() {
        return placeAddress;
    }

    public void setPlaceAddress(String placeAddress) {
        this.placeAddress = placeAddress;
    }

    public String getAggregationInfo() {
        return aggregationInfo;
    }

    public void setAggregationInfo(String aggregationInfo) {
        this.aggregationInfo = aggregationInfo;
    }

    public String getWalkGoupRootId() {
        return walkGoupRootId;
    }

    public void setWalkGoupRootId(String walkGoupRootId) {
        this.walkGoupRootId = walkGoupRootId;
    }

    public double getStopDriveSpeed() {
        return stopDriveSpeed;
    }

    public void setStopDriveSpeed(double stopDriveSpeed) {
        this.stopDriveSpeed = stopDriveSpeed;
    }

    @Override
    public String toString() {
        return "PlannedOrder{" +
                "resourceId='" + resourceId + '\'' +
                ", dayId='" + dayId + '\'' +
                ", stopId='" + stopId + '\'' +
                ", stopPosition=" + stopPosition +
                ", stopY=" + stopY +
                ", stopX=" + stopX +
                ", stopType=" + stopType +
                ", stopDriveTime='" + stopDriveTime + '\'' +
                ", stopStartTime='" + stopStartTime + '\'' +
                ", stopDuration='" + stopDuration + '\'' +
                ", stopStatus=" + stopStatus +
                ", stopDriveDistance=" + stopDriveDistance +
                ", stopElapsedDistance=" + stopElapsedDistance +
                ", placeName='" + placeName + '\'' +
                ", placeAddress='" + placeAddress + '\'' +
                ", aggregationInfo='" + aggregationInfo + '\'' +
                ", walkGoupRootId='" + walkGoupRootId + '\'' +
                ", stopDriveSpeed=" + stopDriveSpeed +
                '}';
    }
}
