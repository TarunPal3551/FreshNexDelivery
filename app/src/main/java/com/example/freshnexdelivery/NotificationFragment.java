package com.example.freshnexdelivery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class NotificationFragment extends Fragment {
    RecyclerView recyclerViewNotification;
    NotificationAdapter notificationAdapter;
    ArrayList<GuidlineData> productImageArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        recyclerViewNotification = (RecyclerView) view.findViewById(R.id.notificationRecyclerView);
        productImageArrayList = new ArrayList<>();
        productImageArrayList.add(new GuidlineData(R.drawable.guide1, "Wear Mask", "Wear Mask"));
        productImageArrayList.add(new GuidlineData(R.drawable.guide2, "Wear Mask", "Wear Mask"));
        productImageArrayList.add(new GuidlineData(R.drawable.guide3, "Wear Mask", "Wear Mask"));
        productImageArrayList.add(new GuidlineData(R.drawable.guide4, "Wear Mask", "Wear Mask"));
        productImageArrayList.add(new GuidlineData(R.drawable.guide5, "Wear Mask", "Wear Mask"));
        recyclerViewNotification.setHasFixedSize(true);
        recyclerViewNotification.setLayoutManager(new LinearLayoutManager(getContext()));
        notificationAdapter = new NotificationAdapter(getContext(), productImageArrayList);
        recyclerViewNotification.setAdapter(notificationAdapter);
        return view;
    }
}