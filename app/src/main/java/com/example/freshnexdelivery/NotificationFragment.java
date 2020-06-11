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
        productImageArrayList.add(new GuidlineData(R.drawable.guide1, "Wear Face Mask", "Keep your face covered with a face mask and avoid touching your face."));
        productImageArrayList.add(new GuidlineData(R.drawable.guide2, "Wash Your Hands", "Wash your hands frequently with soap or use hand sanitizer every timme."));
        productImageArrayList.add(new GuidlineData(R.drawable.guide3, "Check Temperature", "Check your body temperature with thermal screening device every morning."));
        productImageArrayList.add(new GuidlineData(R.drawable.guide4, "No Contact Delivery", "We believe in touch free delivery so keep the parcel outside the door."));
        productImageArrayList.add(new GuidlineData(R.drawable.guide5, "Defeat Corona Virus", "Follow the above guidelines to defeat Corona virus to affect you and others."));
        recyclerViewNotification.setHasFixedSize(true);
        recyclerViewNotification.setLayoutManager(new LinearLayoutManager(getContext()));
        notificationAdapter = new NotificationAdapter(getContext(), productImageArrayList);
        recyclerViewNotification.setAdapter(notificationAdapter);
        return view;
    }
}