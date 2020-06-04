package com.example.freshnexdelivery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class NotificationFragment extends Fragment {
    RecyclerView recyclerViewNotification;
    NotificationAdapter notificationAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        recyclerViewNotification = (RecyclerView) view.findViewById(R.id.notificationRecyclerView);
        recyclerViewNotification.setHasFixedSize(true);
        recyclerViewNotification.setLayoutManager(new LinearLayoutManager(getContext()));
        notificationAdapter = new NotificationAdapter(getContext());
        recyclerViewNotification.setAdapter(notificationAdapter);
        return view;
    }
}