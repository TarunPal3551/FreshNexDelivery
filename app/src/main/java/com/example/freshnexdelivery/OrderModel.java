package com.example.freshnexdelivery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OrderModel {
    @SerializedName("error")
    @Expose
    private Boolean error;

    @SerializedName("data")
    @Expose
    private ArrayList<OrderData> data;

    public OrderModel(Boolean error, ArrayList<OrderData> data) {
        this.error = error;
        this.data = data;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ArrayList<OrderData> getData() {
        return data;
    }

    public void setData(ArrayList<OrderData> data) {
        this.data = data;
    }
}
