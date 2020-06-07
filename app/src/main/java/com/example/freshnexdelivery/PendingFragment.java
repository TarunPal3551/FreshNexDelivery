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

public class PendingFragment extends Fragment {
    RecyclerView recyclerViewPending;
    PendingAdapter pendingAdapter;
    API_Interface api_interface;
    Preferences preferences;


    public PendingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pending, container, false);
        preferences = new Preferences(getActivity());
        recyclerViewPending = (RecyclerView) view.findViewById(R.id.pendingRecyclerView);
        recyclerViewPending.setHasFixedSize(true);
        recyclerViewPending.setLayoutManager(new LinearLayoutManager(getContext()));
        pendingAdapter = new PendingAdapter(getContext());
        recyclerViewPending.setAdapter(pendingAdapter);

        return view;
    }

    public void getPendingOrders() {
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