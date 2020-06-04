package com.example.freshnexdelivery;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DeliveredFragment extends Fragment {
    RecyclerView recyclerViewPending;
    DeliveryAdapter deliveryAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_delivered, container, false);
        recyclerViewPending=(RecyclerView)view.findViewById(R.id.pendingRecyclerView);
        recyclerViewPending.setHasFixedSize(true);
        recyclerViewPending.setLayoutManager(new LinearLayoutManager(getContext()));
        deliveryAdapter=new DeliveryAdapter(getContext());
        recyclerViewPending.setAdapter(deliveryAdapter);
        return view;
    }
}