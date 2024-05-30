package com.cesar.toursolvermobile2.model;

import com.google.gson.annotations.SerializedName;

public class EvaluationInfos {
    @SerializedName("orderOriginalResourceId")
    private String orderOriginalResourceId;

    @SerializedName("orderOriginalVisitDay")
    private String orderOriginalVisitDay;

    @SerializedName("orderPosition")
    private String orderPosition;

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
