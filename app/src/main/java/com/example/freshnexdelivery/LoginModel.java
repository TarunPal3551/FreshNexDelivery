package com.example.freshnexdelivery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModel {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("data")
    @Expose
    private Data data;

    public LoginModel(Boolean error, Data data) {
        this.error = error;
        this.data = data;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
