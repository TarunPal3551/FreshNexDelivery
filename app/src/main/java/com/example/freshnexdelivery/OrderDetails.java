package com.example.freshnexdelivery;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetails extends AppCompatActivity {
    RecyclerView recyclerViewProducts;
    ProductAdapter productAdapter;
    OrderData orderData;
    Preferences preferences;
    API_Interface api_interface;
    LinearLayout buttonLayout;
    MaterialButton deliveredButton, cancelButton;
    private static final String TAG = "OrderDetails";
    ArrayList<Product> productArrayList = new ArrayList<>();
    FloatingActionButton floatingCallButton;
    TextView textViewOrderId, textViewPrice, textViewDeliveryDate, textViewDeliveryTime, textViewTotalItems, textViewUserName, textViewAddress, textViewStatus, textViewPaymentMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        preferences = new Preferences(this);
        buttonLayout = (LinearLayout) findViewById(R.id.layoutButton);
        deliveredButton = (MaterialButton) findViewById(R.id.deliveredButton);
        cancelButton = (MaterialButton) findViewById(R.id.cancelledButton);
        floatingCallButton = (FloatingActionButton) findViewById(R.id.floatingCallButton);
        textViewOrderId = (TextView) findViewById(R.id.textViewOrderId);
        textViewAddress = (TextView) findViewById(R.id.textViewAddress);
        textViewDeliveryDate = (TextView) findViewById(R.id.textViewDeliveryDate);
        textViewDeliveryTime = (TextView) findViewById(R.id.textViewDeliveryTime);
        textViewPrice = (TextView) findViewById(R.id.textViewPrice);
        textViewTotalItems = (TextView) findViewById(R.id.textViewTotalItems);
        textViewStatus = (TextView) findViewById(R.id.textViewStatus);
        textViewUserName = (TextView) findViewById(R.id.textViewUsername);


        recyclerViewProducts = (RecyclerView) findViewById(R.id.recyclerViewProducts);
        recyclerViewProducts.setHasFixedSize(true);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));
        productAdapter = new ProductAdapter(this, productArrayList);
        recyclerViewProducts.setAdapter(productAdapter);
        orderData = preferences.getProductDetailsJson();
//        StringTokenizer tokenName = new StringTokenizer(orderData.getPname(), getString(R.string.splitby));
//        StringTokenizer tokenprice = new StringTokenizer(orderData.getPname(), getString(R.string.splitby));
//        StringTokenizer tokenquantity = new StringTokenizer(orderData.getPname(), getString(R.string.splitby));
//        StringTokenizer tokenq_type = new StringTokenizer(orderData.getPname(), getString(R.string.splitby));
        textViewPrice.setText("₹" + orderData.getTotal());
        textViewUserName.setText("" + orderData.getUser().get(0).getName());
        textViewAddress.setText("" + orderData.getUser().get(0).getHno() + ", " + orderData.getUser().get(0).getSociety() + "\n" + orderData.getUser().get(0).getArea() + ", " + orderData.getUser().get(0).getPincode());
        textViewOrderId.setText("Order Id #" + orderData.getOid());
        textViewDeliveryDate.setText("Delivery Date " + orderData.getDdate());
        textViewDeliveryTime.setText("Delivery Time " + orderData.getTimesloat());
        textViewStatus.setText("" + orderData.getpMethod());

        String[] name = orderData.getPname().toString().split(getResources().getString(R.string.splitby));
        String[] price = orderData.getPprice().split(getResources().getString(R.string.splitby));
        String[] quantity = orderData.getQty().split(getResources().getString(R.string.splitby));
        String[] q_type = orderData.getPtype().split(getResources().getString(R.string.splitby));
        String[] id = orderData.getPid().split(getResources().getString(R.string.splitby));
        for (int i = 0; i < name.length; i++) {
            productArrayList.add(new Product((name[i].replace("$", "")), "₹ " + price[i].replace("$", ""), quantity[i].replace("$", ""), q_type[i].replace("$", ""), id[i].replace("$", "")));
        }
        textViewTotalItems.setText("" + productArrayList.size());

        productAdapter = new ProductAdapter(this, productArrayList);
        recyclerViewProducts.setAdapter(productAdapter);
        if (orderData.getStatus().equals("cancelled") && orderData.getStatus().equals("delivered")) {
            buttonLayout.setVisibility(View.GONE);
        } else {
            buttonLayout.setVisibility(View.VISIBLE);
        }
        deliveredButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeOrderStatus("delivered");
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeOrderStatus("cancelled");
            }
        });

    }

    public void changeOrderStatus(String status) {

        api_interface = RetrofitClient.getClient().getApi();
        api_interface.changeOrderStatus(preferences.getToken(), status, orderData.getOid()).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.d(TAG, "onResponse: " + response.body());
                if (response.body() != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                        if (!jsonObject.getBoolean("error")) {
                            Toast.makeText(OrderDetails.this, "Status Updated", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {

                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }
}