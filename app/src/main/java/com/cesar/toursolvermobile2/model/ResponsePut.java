package com.cesar.toursolvermobile2.model;

import com.google.gson.annotations.SerializedName;

public class ResponsePut {
    @SerializedName("mesage")
    private String message;

    @SerializedName("status")
    private String status;

    @SerializedName("errCode")
    private String errCode;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    @Override
    public String toString() {
        return "ResponsePut{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", errCode='" + errCode + '\'' +
                '}';
    }
}
