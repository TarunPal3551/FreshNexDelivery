package com.example.freshnexdelivery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetDialog;
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
    LinearLayout customerDetailsLayout;
    MaterialToolbar toolbar;
    ProgressDialog progressDialog;
    ProductImage productImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        progressDialog = new ProgressDialog(OrderDetails.this);
        progressDialog.setMessage("Processing...");
        progressDialog.setCancelable(false);
        toolbar = (MaterialToolbar) findViewById(R.id.toolbar);
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
        customerDetailsLayout = (LinearLayout) findViewById(R.id.customerDetailsLayout);


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
        //TODO Product Image and User Data Details to be visible
        if (orderData.getUser() != null && orderData.getUser().size() > 0) {
            textViewUserName.setText("" + orderData.getUser().get(0).getName());
            textViewAddress.setText("" + orderData.getUser().get(0).getHno() + ", " + orderData.getUser().get(0).getSociety() + "\n" + orderData.getUser().get(0).getArea() + ", " + orderData.getUser().get(0).getPincode());
        }
        textViewOrderId.setText("Order Id #" + orderData.getOid());
        textViewDeliveryDate.setText("Delivery Date " + orderData.getDdate().replaceFirst("--", ""));
        textViewDeliveryTime.setText("Delivery Time " + orderData.getTimesloat());
        textViewStatus.setText("" + orderData.getpMethod());

        final String[] name = orderData.getPname().toString().split(getResources().getString(R.string.splitby));
        final String[] price = orderData.getPprice().split(getResources().getString(R.string.splitby));
        final String[] quantity = orderData.getQty().split(getResources().getString(R.string.splitby));
        final String[] q_type = orderData.getPtype().split(getResources().getString(R.string.splitby));
        final String[] id = orderData.getPid().split(getResources().getString(R.string.splitby));
        for (int i = 0; i < name.length; i++) {
            API_Interface api_interface = RetrofitClient.getClient().getApi();
            final int finalI = i;
            api_interface.getProductDetails(id[i]).enqueue(new Callback<PData>() {
                @Override
                public void onResponse(Call<PData> call, Response<PData> response) {
                    Log.d(TAG, "onResponse: " + response.body().getData().getPimg());
                    if (response.body() != null && response.body().getError()) {
                        String imageUrl = response.body().getData().getPimg();
                        productImage = response.body().getData();
                        if (productImage != null) {
                            productArrayList.add(new Product((name[finalI].replace("$", "")), "₹" + price[finalI].replace("$", ""), quantity[finalI].replace("$", ""), q_type[finalI].replace("$", ""), id[finalI].replace("$", ""), imageUrl));
                            productAdapter = new ProductAdapter(OrderDetails.this, productArrayList);
                            recyclerViewProducts.setAdapter(productAdapter);
                        } else {
                            productArrayList.add(new Product((name[finalI].replace("$", "")), "₹" + price[finalI].replace("$", ""), quantity[finalI].replace("$", ""), q_type[finalI].replace("$", ""), id[finalI].replace("$", ""), imageUrl));
                            productAdapter = new ProductAdapter(OrderDetails.this, productArrayList);
                            recyclerViewProducts.setAdapter(productAdapter);
                        }
                    } else {

                        productImage = null;
                        if (productImage != null) {
                            productArrayList.add(new Product((name[finalI].replace("$", "")), "₹" + price[finalI].replace("$", ""), quantity[finalI].replace("$", ""), q_type[finalI].replace("$", ""), id[finalI].replace("$", ""), productImage.getPimg()));
                            productAdapter = new ProductAdapter(OrderDetails.this, productArrayList);
                            recyclerViewProducts.setAdapter(productAdapter);
                        } else {
                            productArrayList.add(new Product((name[finalI].replace("$", "")), "₹" + price[finalI].replace("$", ""), quantity[finalI].replace("$", ""), q_type[finalI].replace("$", ""), id[finalI].replace("$", ""), null));
                            productAdapter = new ProductAdapter(OrderDetails.this, productArrayList);
                            recyclerViewProducts.setAdapter(productAdapter);
                        }
                    }
                }

                @Override
                public void onFailure(Call<PData> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                    if (productImage != null) {
                        productArrayList.add(new Product((name[finalI].replace("$", "")), "₹" + price[finalI].replace("$", ""), quantity[finalI].replace("$", ""), q_type[finalI].replace("$", ""), id[finalI].replace("$", ""), productImage.getPimg()));
                        productAdapter = new ProductAdapter(OrderDetails.this, productArrayList);
                        recyclerViewProducts.setAdapter(productAdapter);
                    } else {
                        productArrayList.add(new Product((name[finalI].replace("$", "")), "₹" + price[finalI].replace("$", ""), quantity[finalI].replace("$", ""), q_type[finalI].replace("$", ""), id[finalI].replace("$", ""), null));
                        productAdapter = new ProductAdapter(OrderDetails.this, productArrayList);
                        recyclerViewProducts.setAdapter(productAdapter);
                    }

                }
            });


        }
        textViewTotalItems.setText("Total Items: " + productArrayList.size());

        if (orderData.getStatus().equals("cancelled") || orderData.getStatus().equals("delivered")) {
            buttonLayout.setVisibility(View.GONE);
            customerDetailsLayout.setVisibility(View.GONE);

        } else {
            buttonLayout.setVisibility(View.VISIBLE);
            customerDetailsLayout.setVisibility(View.VISIBLE);
        }

        deliveredButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ////
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(OrderDetails.this, R.style.AppBottomSheetDialogTheme);
                bottomSheetDialog.setCancelable(true);
                bottomSheetDialog.setCanceledOnTouchOutside(true);
                bottomSheetDialog.setContentView(R.layout.order_cancel_dialog);
//                bottomSheetDialog.set(DialogFragment.STYLE_NO_FRAME, R.style.AppBottomSheetDialogTheme);
                bottomSheetDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                Button yesButton = (Button) bottomSheetDialog.findViewById(R.id.yesButton);
                Button noButton = (Button) bottomSheetDialog.findViewById(R.id.noButton);
                TextView textView = (TextView) bottomSheetDialog.findViewById(R.id.textViewTittle);
                textView.setText("Are you sure you want to Cancel the Order?");
                yesButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.cancel();
                        progressDialog.show();
                        changeOrderStatus("delivered");


                    }
                });
                noButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.show();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ////
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(OrderDetails.this, R.style.AppBottomSheetDialogTheme);
                bottomSheetDialog.setCancelable(true);
                bottomSheetDialog.setCanceledOnTouchOutside(true);
                bottomSheetDialog.setContentView(R.layout.order_cancel_dialog);
//                bottomSheetDialog.set(DialogFragment.STYLE_NO_FRAME, R.style.AppBottomSheetDialogTheme);
                bottomSheetDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                Button yesButton = (Button) bottomSheetDialog.findViewById(R.id.yesButton);
                Button noButton = (Button) bottomSheetDialog.findViewById(R.id.noButton);
                TextView textViewTittle = (TextView) bottomSheetDialog.findViewById(R.id.textViewTittle);
                textViewTittle.setText("Are you sure you want to Cancel the Order?");
                yesButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.cancel();
                        progressDialog.show();
                        changeOrderStatus("cancelled");


                    }
                });
                noButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.show();

            }
        });
        floatingCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + orderData.getUser().get(0).getMobile()));
                startActivity(intent);
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public String getProductImage(String id) {
        final String[] imagePath = {""};

        return imagePath[0];
    }

    public void changeOrderStatus(String status) {
        api_interface = RetrofitClient.getClient().getApi();
        api_interface.changeOrderStatus(preferences.getToken(), status, orderData.getOid()).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.d(TAG, "onResponse: " + response.body());
                progressDialog.dismiss();
                if (response.code() == 200) {
                    try {

                        JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                        if (!jsonObject.getBoolean("error")) {
                            Toast.makeText(OrderDetails.this, "Status Updated", Toast.LENGTH_SHORT).show();
                            buttonLayout.setVisibility(View.GONE);
                            Intent intent = new Intent(OrderDetails.this, MainActivity.class);
                            startActivity(intent);
                            finish();
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