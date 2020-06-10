package com.example.freshnexdelivery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

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
    RelativeLayout errorLayout;
    ProgressBar progressBar;
    MaterialToolbar materialToolbar;

    public PendingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pending, container, false);
        materialToolbar = (MaterialToolbar) view.findViewById(R.id.toolbar);
        materialToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.refresh) {
                    getPendingOrders();
                }
                return true;
            }
        });
        orderDataArrayList = new ArrayList<>();
        preferences = new Preferences(getActivity());
        recyclerViewPending = (RecyclerView) view.findViewById(R.id.pendingRecyclerView);
        errorLayout = (RelativeLayout) view.findViewById(R.id.errorLayout);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        recyclerViewPending.setHasFixedSize(true);
        recyclerViewPending.setLayoutManager(new LinearLayoutManager(getContext()));
        pendingAdapter = new PendingAdapter(getContext(), orderDataArrayList);
        recyclerViewPending.setAdapter(pendingAdapter);
        getPendingOrders();
        return view;
    }

    public void getPendingOrders() {
        errorLayout.setVisibility(View.GONE);
        recyclerViewPending.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        orderDataArrayList = new ArrayList<>();
        api_interface = RetrofitClient.getClient().getApi();
        api_interface.getPendingOrders(preferences.getToken()).enqueue(new Callback<OrderModel>() {
            @Override
            public void onResponse(Call<OrderModel> call, Response<OrderModel> response) {
                Log.d(TAG, "onResponse: " + response.body().getError());
                if (!response.body().getError()) {
                    orderDataArrayList = response.body().getData();
                    if (orderDataArrayList.size() > 0) {
                        pendingAdapter = new PendingAdapter(getContext(), orderDataArrayList);
                        recyclerViewPending.setAdapter(pendingAdapter);
                        errorLayout.setVisibility(View.GONE);
                        recyclerViewPending.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    } else {
                        errorLayout.setVisibility(View.VISIBLE);
                        recyclerViewPending.setVisibility(View.GONE);
                        progressBar.setVisibility(View.GONE);
//                        Toast.makeText(getActivity(), "No Pending Orders", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    errorLayout.setVisibility(View.VISIBLE);
                    recyclerViewPending.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);

                    // Toast.makeText(getActivity(), "Token Expired...Login Again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderModel> call, Throwable t) {
                errorLayout.setVisibility(View.VISIBLE);
                recyclerViewPending.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}