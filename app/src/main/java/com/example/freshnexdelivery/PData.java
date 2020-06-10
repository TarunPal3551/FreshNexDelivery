package com.example.freshnexdelivery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PData {
    @SerializedName("error")
    @Expose
    private Boolean error;

    @SerializedName("data")
    @Expose
    private ProductImage data;

    public PData(Boolean error, ProductImage data) {
        this.error = error;
        this.data = data;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ProductImage getData() {
        return data;
    }

    public void setData(ProductImage data) {
        this.data = data;
    }
}
