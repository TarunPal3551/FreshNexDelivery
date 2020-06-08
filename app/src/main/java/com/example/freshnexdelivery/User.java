package com.example.freshnexdelivery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("hno")
    @Expose
    private String hno;
    @SerializedName("society")
    @Expose
    private String society;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("landmark")
    @Expose
    private String landmark;

    public User(String name, String mobile, String hno, String society, String area, String pincode, String landmark) {
        this.name = name;
        this.mobile = mobile;
        this.hno = hno;
        this.society = society;
        this.area = area;
        this.pincode = pincode;
        this.landmark = landmark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHno() {
        return hno;
    }

    public void setHno(String hno) {
        this.hno = hno;
    }

    public String getSociety() {
        return society;
    }

    public void setSociety(String society) {
        this.society = society;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }
}
