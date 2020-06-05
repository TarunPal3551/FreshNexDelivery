package com.example.freshnexdelivery;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    private static RetrofitClient minstance;
    private static final String BASE_URL = "https://avilaksh.com/freshnex/admin/api/deliveryagent/";

    private RetrofitClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(5, 50, TimeUnit.SECONDS)).build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();


    }
    public static synchronized RetrofitClient getClient(){
        if(minstance==null){
            minstance=new RetrofitClient();

        }
        return minstance;

    }


    public API_Interface getApi(){
        return retrofit.create(API_Interface.class);
    }

}
