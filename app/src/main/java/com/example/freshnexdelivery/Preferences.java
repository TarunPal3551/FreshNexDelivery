package com.example.freshnexdelivery;

import android.content.Context;
import android.content.SharedPreferences;

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

    public void setReset(boolean value) {
        editor.putBoolean(Constant.Password_Reset, value);
        editor.commit();
    }

    public boolean getReset() {
        return sharedPreferences.getBoolean(Constant.Password_Reset, false);
    }


}
