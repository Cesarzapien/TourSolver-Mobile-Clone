package com.cesar.toursolvermobile2.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Data implements Parcelable {
    private String Data;

    @Override
    public String toString() {
        return "Data{" +
                "Data='" + Data + '\'' +
                '}';
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    protected Data(Parcel in) {
        Data = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Data);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };
}
