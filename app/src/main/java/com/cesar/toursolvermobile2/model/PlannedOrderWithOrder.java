package com.cesar.toursolvermobile2.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PlannedOrderWithOrder implements Parcelable {
    private PlannedOrder plannedOrder;
    private Order order;

    public PlannedOrderWithOrder(PlannedOrder plannedOrder, Order order) {
        this.plannedOrder = plannedOrder;
        this.order = order;
    }

    protected PlannedOrderWithOrder(Parcel in) {
        plannedOrder = in.readParcelable(PlannedOrder.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(plannedOrder, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PlannedOrderWithOrder> CREATOR = new Creator<PlannedOrderWithOrder>() {
        @Override
        public PlannedOrderWithOrder createFromParcel(Parcel in) {
            return new PlannedOrderWithOrder(in);
        }

        @Override
        public PlannedOrderWithOrder[] newArray(int size) {
            return new PlannedOrderWithOrder[size];
        }
    };

    public PlannedOrder getPlannedOrder() {
        return plannedOrder;
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "PlannedOrderWithOrder{" +
                "plannedOrder=" + plannedOrder +
                ", order=" + order +
                '}';
    }
}

