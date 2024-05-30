package com.cesar.toursolvermobile2.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ApiResponse implements Parcelable {
    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private String status;
    @SerializedName("errCode")
    private String errCode;
    @SerializedName("operationalOrderAchievements")
    private List<OperationalOrderAchievement> operationalOrderAchievements;
    @SerializedName("lastKnownPosition")
    private List<LastKnownPosition> lastKnownPosition;

    // Getters y setters


    protected ApiResponse(Parcel in) {
        message = in.readString();
        status = in.readString();
        errCode = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(message);
        dest.writeString(status);
        dest.writeString(errCode);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ApiResponse> CREATOR = new Creator<ApiResponse>() {
        @Override
        public ApiResponse createFromParcel(Parcel in) {
            return new ApiResponse(in);
        }

        @Override
        public ApiResponse[] newArray(int size) {
            return new ApiResponse[size];
        }
    };

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

    public List<OperationalOrderAchievement> getOperationalOrderAchievements() {
        return operationalOrderAchievements;
    }

    public void setOperationalOrderAchievements(List<OperationalOrderAchievement> operationalOrderAchievements) {
        this.operationalOrderAchievements = operationalOrderAchievements;
    }

    public List<LastKnownPosition> getLastKnownPosition() {
        return lastKnownPosition;
    }

    public void setLastKnownPosition(List<LastKnownPosition> lastKnownPosition) {
        this.lastKnownPosition = lastKnownPosition;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", errCode='" + errCode + '\'' +
                ", operationalOrderAchievements=" + operationalOrderAchievements +
                ", lastKnownPosition=" + lastKnownPosition +
                '}';
    }
}


