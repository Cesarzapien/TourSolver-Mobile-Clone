package com.cesar.toursolvermobile2.model;

import com.google.gson.annotations.SerializedName;

public class TravelTimeModifier {
    @SerializedName("value")
    private String value;

    @SerializedName("length")
    private String length;

    @SerializedName("offset")
    private String offset;

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
