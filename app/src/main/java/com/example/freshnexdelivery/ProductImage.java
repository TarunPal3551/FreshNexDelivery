package com.example.freshnexdelivery;

import com.google.gson.annotations.SerializedName;

public class ProductImage {
    @SerializedName("id")
    String id;
    @SerializedName("pimg")
    String pimg;
    @SerializedName("pname")
    String name;

    public ProductImage(String id, String pimg, String name) {
        this.id = id;
        this.pimg = pimg;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPimg() {
        return pimg;
    }

    public void setPimg(String pimg) {
        this.pimg = pimg;
    }
}
