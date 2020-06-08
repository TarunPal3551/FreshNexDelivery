package com.example.freshnexdelivery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveredFragment extends Fragment {
    RecyclerView recyclerViewPending;
    DeliveryAdapter deliveryAdapter;
    API_Interface api_interface;
    Preferences preferences;
    ArrayList<OrderData> orderDataArrayList = new ArrayList<>();
    private static final String TAG = "DeliveredFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delivered, container, false);
        preferences = new Preferences(getActivity());
        recyclerViewPending = (RecyclerView) view.findViewById(R.id.deliveredRecyclerView);
        recyclerViewPending.setHasFixedSize(true);
        recyclerViewPending.setLayoutManager(new LinearLayoutManager(getContext()));
        deliveryAdapter = new DeliveryAdapter(getContext(), orderDataArrayList);
        recyclerViewPending.setAdapter(deliveryAdapter);
        getPendingOrders();
        return view;
    }

    public void getPendingOrders() {
        orderDataArrayList = new ArrayList<>();
        api_interface = RetrofitClient.getClient().getApi();
        api_interface.getPendingOrders(preferences.getToken()).enqueue(new Callback<OrderModel>() {
            @Override
            public void onResponse(Call<OrderModel> call, Response<OrderModel> response) {
                Log.d(TAG, "onResponse: " + response.body());
                if (!response.body().getError()) {
                    orderDataArrayList = response.body().getData();
                    deliveryAdapter = new DeliveryAdapter(getContext(), orderDataArrayList);
                    recyclerViewPending.setAdapter(deliveryAdapter);

                } else {
                    Toast.makeText(getContext(), "Token Expired...Login Again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderModel> call, Throwable t) {

            }
        });
    }
}