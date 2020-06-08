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

    @GET("changeavailability.php")
    Call<Object> changeStatus(@Query("token") String token, @Query("status") String status);

    @GET("pendingorders.php")
    Call<OrderModel> getPendingOrders(@Query("token") String token);

    @GET("orderhistory.php")
    Call<OrderModel> getOrders(@Query("token") String token);

    @GET("orderstatus.php")
    Call<Object> changeOrderStatus(@Query("token") String token,
                                   @Query("status") String status,
                                   @Query("order_id") String order_id);

}
