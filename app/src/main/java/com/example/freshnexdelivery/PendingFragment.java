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

public class PendingFragment extends Fragment {
    RecyclerView recyclerViewPending;
    PendingAdapter pendingAdapter;
    API_Interface api_interface;
    Preferences preferences;
    ArrayList<OrderData> orderDataArrayList;
    private static final String TAG = "PendingFragment";

    public PendingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pending, container, false);

        orderDataArrayList = new ArrayList<>();
        preferences = new Preferences(getActivity());
        recyclerViewPending = (RecyclerView) view.findViewById(R.id.pendingRecyclerView);
        recyclerViewPending.setHasFixedSize(true);
        recyclerViewPending.setLayoutManager(new LinearLayoutManager(getContext()));
        pendingAdapter = new PendingAdapter(getContext(), orderDataArrayList);
        recyclerViewPending.setAdapter(pendingAdapter);
        getPendingOrders();

        return view;
    }

    public void getPendingOrders() {
        orderDataArrayList = new ArrayList<>();
        api_interface = RetrofitClient.getClient().getApi();
        api_interface.getPendingOrders(preferences.getToken()).enqueue(new Callback<OrderModel>() {
            @Override
            public void onResponse(Call<OrderModel> call, Response<OrderModel> response) {
                Log.d(TAG, "onResponse: " + response.body().getError());
                if (!response.body().getError()) {
                    orderDataArrayList = response.body().getData();
                    if (orderDataArrayList.size()>0){
                        pendingAdapter = new PendingAdapter(getContext(), orderDataArrayList);
                        recyclerViewPending.setAdapter(pendingAdapter);
                    }
                    else {
                        Toast.makeText(getActivity(), "No Pending Orders", Toast.LENGTH_SHORT).show();
                    }


                } else {

                    Toast.makeText(getActivity(), "Token Expired...Login Again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderModel> call, Throwable t) {

            }
        });
    }
}