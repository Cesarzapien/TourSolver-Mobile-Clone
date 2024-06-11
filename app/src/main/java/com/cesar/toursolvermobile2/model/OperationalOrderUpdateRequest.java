package com.cesar.toursolvermobile2.model;

import com.google.gson.annotations.SerializedName;

public class OperationalOrderUpdateRequest {
    @SerializedName("achievedEndDate")
    private long achievedEndDate;
    @SerializedName("achievedEndPositionLat")
    private double achievedEndPositionLat;
    @SerializedName("achievedEndPositionLon")
    private double achievedEndPositionLon;
    @SerializedName("achievedStartDate")
    private long achievedStartDate;
    @SerializedName("achievedStartPositionLat")
    private double achievedStartPositionLat;
    @SerializedName("achievedStartPositionLon")
    private double achievedStartPositionLon;
    @SerializedName("data")
    private Object data;
    @SerializedName("operationalOrderId")
    private String operationalOrderId;
    @SerializedName("scans")
    private Object[] scans;
    @SerializedName("status")
    private String status;
    @SerializedName("svgSignature")
    private Object svgSignature;
    @SerializedName("userLogin")
    private String userLogin;

    public OperationalOrderUpdateRequest(long achievedEndDate, double achievedEndPositionLat, double achievedEndPositionLon, long achievedStartDate, double achievedStartPositionLat, double achievedStartPositionLon, String operationalOrderId, String status, String userLogin) {
        this.achievedEndDate = achievedEndDate;
        this.achievedEndPositionLat = achievedEndPositionLat;
        this.achievedEndPositionLon = achievedEndPositionLon;
        this.achievedStartDate = achievedStartDate;
        this.achievedStartPositionLat = achievedStartPositionLat;
        this.achievedStartPositionLon = achievedStartPositionLon;
        this.data = new Object();
        this.operationalOrderId = operationalOrderId;
        this.scans = new Object[]{};
        this.status = status;
        this.svgSignature = null;
        this.userLogin = userLogin;
    }
}
