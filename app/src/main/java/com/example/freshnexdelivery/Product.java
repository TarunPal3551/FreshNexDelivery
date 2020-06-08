package com.example.freshnexdelivery;

public class Product {
    String  name;
    String price;
    String quantity;
    String quantity_Type;
    String  Id;

    public Product(String name, String price, String quantity, String quantity_Type, String id) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.quantity_Type = quantity_Type;
        Id = id;
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
