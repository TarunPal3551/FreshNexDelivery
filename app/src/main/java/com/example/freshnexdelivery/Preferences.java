package com.example.freshnexdelivery;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class Preferences {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public Preferences(Context mContext) {
        sharedPreferences = mContext.getSharedPreferences("FreshNexDelivery", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setToken(String token) {
        editor.putString(Constant.Token, token);
        editor.commit();
    }

    public String getToken() {
        return sharedPreferences.getString(Constant.Token, "");
    }

    public void setStatus(int value) {
        editor.putInt(Constant.Status, value);
        editor.commit();
    }

    public int getStatus() {
        return sharedPreferences.getInt(Constant.Status, 0);
    }

    public void setName(String token) {
        editor.putString(Constant.NAME, token);
        editor.commit();
    }

    public String getName() {
        return sharedPreferences.getString(Constant.NAME, "");
    }

    public void setPartnerId(String token) {
        editor.putString(Constant.partner_Id, token);
        editor.commit();
    }

    public String getPartnerId() {
        return sharedPreferences.getString(Constant.partner_Id, "");
    }

    public void setId(int token) {
        editor.putInt(Constant.Id, token);
        editor.commit();
    }

    public int getId() {
        return sharedPreferences.getInt(Constant.Id, 0);
    }

    public void setPhone(String token) {
        editor.putString(Constant.PHONE, token);
        editor.commit();
    }

    public String getPhone() {
        return sharedPreferences.getString(Constant.PHONE, "");
    }

    public void setEmail(String token) {
        editor.putString(Constant.Email, token);
        editor.commit();
    }

    public String getEmail() {
        return sharedPreferences.getString(Constant.Email, "");
    }

    public void setAadharNumber(String token) {
        editor.putString(Constant.AADHAR, token);
        editor.commit();
    }

    public String getAadharNumber() {
        return sharedPreferences.getString(Constant.AADHAR, "");
    }

    public void setVehicalType(String token) {
        editor.putString(Constant.Vehical_Type, token);
        editor.commit();
    }

    public String getVehicalType() {
        return sharedPreferences.getString(Constant.Vehical_Type, "");
    }

    public void setVehicalNum(String token) {
        editor.putString(Constant.VehicalNumber, token);
        editor.commit();
    }

    public String getVehicalNum() {
        return sharedPreferences.getString(Constant.VehicalNumber, "");
    }

    public void setReset(boolean value) {
        editor.putBoolean(Constant.Password_Reset, value);
        editor.commit();
    }

    public boolean getReset() {
        return sharedPreferences.getBoolean(Constant.Password_Reset, false);
    }

    public void setProductDetails(OrderData productDetails) {
        Gson gson = new Gson();
        String jsonObject = gson.toJson(productDetails);
        editor.putString(Constant.Product_Details, jsonObject);
        editor.commit();

    }

    public OrderData getProductDetailsJson() {
        String json = sharedPreferences.getString(Constant.Product_Details, "");
        Gson gson = new Gson();
        OrderData product = gson.fromJson(json, OrderData.class);
        return product;
    }

}
