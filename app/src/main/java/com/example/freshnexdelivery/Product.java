package com.example.freshnexdelivery;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("pname")
    String name;
    @SerializedName("pprice")
    String price;
    @SerializedName("pgms")
    String quantity;
    @SerializedName("discount")
    String quantity_Type;

    @SerializedName("id")
    String Id;
    @SerializedName("pimg")
    String imageUrl;

    public Product(String name, String price, String quantity, String quantity_Type, String id, String imageUrl) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.quantity_Type = quantity_Type;
        this.Id = id;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuantity_Type() {
        return quantity_Type;
    }

    public void setQuantity_Type(String quantity_Type) {
        this.quantity_Type = quantity_Type;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
