package com.example.freshnexdelivery;







import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface API_Interface {
    @POST("login.php")
    @FormUrlEncoded
    Call<LoginModel> login(@Field("mobile") String mobile, @Field("password") String password);

    @POST("changepassword.php")
    @FormUrlEncoded
    Call<Object> resetPassword(@Query("token") String token, @Field("old_password") String password,
                               @Field("new_password") String newPassword);

    @GET("changeavailability.php?token={remember_token}&status={status}")
    Call<Object> changeStatus(@Path("remember_token") String token, @Path("status") String status);

    @GET("pendingorders.php")
    Call<Object> getPendingOrders(@Query("token") String token);

    @GET("orderhistory.php")
    Call<Object> getOrders(@Query("token") String token);

    @GET("orderstatus.php?token={remember_token}&status={status}&order_id={order_id}")
    Call<Object> changeOrderStatus(@Path("remember_token") String token,
                                   @Path("status") String status,
                                   @Path("order_id") String order_id);

}
