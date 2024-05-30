package com.cesar.toursolvermobile2.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CustomDataMap implements Parcelable {
    private String customDataMap;

    @Override
    public String toString() {
        return "CustomDataMap{" +
                "customDataMap='" + customDataMap + '\'' +
                '}';
    }

    public String getCustomDataMap() {
        return customDataMap;
    }

    public void setCustomDataMap(String customDataMap) {
        this.customDataMap = customDataMap;
    }

    protected CustomDataMap(Parcel in) {
        customDataMap = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(customDataMap);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CustomDataMap> CREATOR = new Creator<CustomDataMap>() {
        @Override
        public CustomDataMap createFromParcel(Parcel in) {
            return new CustomDataMap(in);
        }

        @Override
        public CustomDataMap[] newArray(int size) {
            return new CustomDataMap[size];
        }
    };
}
