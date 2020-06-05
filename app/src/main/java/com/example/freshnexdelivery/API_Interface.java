package com.example.freshnexdelivery;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API_Interface {
    @POST("login.php")
    Call<JSONObject> login(@Body Map<String, String> body);

    @POST("changepassword.php?token={remember_token}")
    Call<Object> resetPassword(@Path("remember_token") String token, @Body Map<String, String> body);

    @GET("changeavailability.php?token={remember_token}&status={status}")
    Call<Object> changeStatus(@Path("remember_token") String token, @Path("status") String status);

    @GET("pendingorders.php?token={remember_token}")
    Call<Object> getPendingOrders(@Path("remember_token") String token);

    @GET("orderhistory.php?token={remember_token}")
    Call<Object> getOrders(@Path("remember_token") String token);

    @GET("orderstatus.php?token={remember_token}&status={status}&order_id={order_id}")
    Call<Object> changeOrderStatus(@Path("remember_token") String token,
                                   @Path("status") String status,
                                   @Path("order_id") String order_id);

}
