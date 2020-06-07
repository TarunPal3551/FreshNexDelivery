package com.example.freshnexdelivery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveredFragment extends Fragment {
    RecyclerView recyclerViewPending;
    DeliveryAdapter deliveryAdapter;
    API_Interface api_interface;
    Preferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delivered, container, false);
        preferences = new Preferences(getActivity());
        recyclerViewPending = (RecyclerView) view.findViewById(R.id.deliveredRecyclerView);
        recyclerViewPending.setHasFixedSize(true);
        recyclerViewPending.setLayoutManager(new LinearLayoutManager(getContext()));
        deliveryAdapter = new DeliveryAdapter(getContext());
        recyclerViewPending.setAdapter(deliveryAdapter);
        return view;
    }

    public void getOrders() {
        api_interface = RetrofitClient.getClient().getApi();
        api_interface.getPendingOrders(preferences.getToken()).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.code() == 200) {

                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }
}