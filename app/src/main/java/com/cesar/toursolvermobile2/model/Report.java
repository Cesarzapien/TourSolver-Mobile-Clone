package com.cesar.toursolvermobile2.model;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class Report {
    @SerializedName("dataItem")
    private String [] dataItem;

    @SerializedName("detailSet")
    private String [] detailSet;

    public String[] getDataItem() {
        return dataItem;
    }

    public void setDataItem(String[] dataItem) {
        this.dataItem = dataItem;
    }

    public String[] getDetailSet() {
        return detailSet;
    }

    public void setDetailSet(String[] detailSet) {
        this.detailSet = detailSet;
    }

    @Override
    public String toString() {
        return "Report{" +
                "dataItem=" + Arrays.toString(dataItem) +
                ", detailSet=" + Arrays.toString(detailSet) +
                '}';
    }
}
