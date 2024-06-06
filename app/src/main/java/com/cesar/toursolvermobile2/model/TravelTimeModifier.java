package com.cesar.toursolvermobile2.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class TravelTimeModifier implements Parcelable {
    @SerializedName("value")
    private String value;

    @SerializedName("length")
    private String length;

    @SerializedName("offset")
    private String offset;

    protected TravelTimeModifier(Parcel in) {
        value = in.readString();
        length = in.readString();
        offset = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(value);
        dest.writeString(length);
        dest.writeString(offset);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TravelTimeModifier> CREATOR = new Creator<TravelTimeModifier>() {
        @Override
        public TravelTimeModifier createFromParcel(Parcel in) {
            return new TravelTimeModifier(in);
        }

        @Override
        public TravelTimeModifier[] newArray(int size) {
            return new TravelTimeModifier[size];
        }
    };

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "TravelTimeModifier{" +
                "value='" + value + '\'' +
                ", length='" + length + '\'' +
                ", offset='" + offset + '\'' +
                '}';
    }
}
