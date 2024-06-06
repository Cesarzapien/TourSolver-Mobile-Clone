package com.cesar.toursolvermobile2.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class EvaluationInfos implements Parcelable {
    @SerializedName("orderOriginalResourceId")
    private String orderOriginalResourceId;

    @SerializedName("orderOriginalVisitDay")
    private String orderOriginalVisitDay;

    @SerializedName("orderPosition")
    private String orderPosition;

    protected EvaluationInfos(Parcel in) {
        orderOriginalResourceId = in.readString();
        orderOriginalVisitDay = in.readString();
        orderPosition = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(orderOriginalResourceId);
        dest.writeString(orderOriginalVisitDay);
        dest.writeString(orderPosition);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<EvaluationInfos> CREATOR = new Creator<EvaluationInfos>() {
        @Override
        public EvaluationInfos createFromParcel(Parcel in) {
            return new EvaluationInfos(in);
        }

        @Override
        public EvaluationInfos[] newArray(int size) {
            return new EvaluationInfos[size];
        }
    };

    public String getOrderOriginalResourceId() {
        return orderOriginalResourceId;
    }

    public void setOrderOriginalResourceId(String orderOriginalResourceId) {
        this.orderOriginalResourceId = orderOriginalResourceId;
    }

    public String getOrderOriginalVisitDay() {
        return orderOriginalVisitDay;
    }

    public void setOrderOriginalVisitDay(String orderOriginalVisitDay) {
        this.orderOriginalVisitDay = orderOriginalVisitDay;
    }

    public String getOrderPosition() {
        return orderPosition;
    }

    public void setOrderPosition(String orderPosition) {
        this.orderPosition = orderPosition;
    }


    @Override
    public String toString() {
        return "EvaluationInfos{" +
                "orderOriginalResourceId='" + orderOriginalResourceId + '\'' +
                ", orderOriginalVisitDay='" + orderOriginalVisitDay + '\'' +
                ", orderPosition='" + orderPosition + '\'' +
                '}';
    }
}
