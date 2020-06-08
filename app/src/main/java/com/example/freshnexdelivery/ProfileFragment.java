package com.example.freshnexdelivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {
    TextView nameTextView, partnerIdTextView, emailTextView, phoneTextView, vehicalTypeTextView, vehicalNumberTextView, aadharTextView;
    CircleImageView circleImageView;
    Preferences preferences;
    MaterialToolbar materialToolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        nameTextView = (TextView) view.findViewById(R.id.nameTextView);
        emailTextView = (TextView) view.findViewById(R.id.emailTextView);
        partnerIdTextView = (TextView) view.findViewById(R.id.partnerId);
        phoneTextView = (TextView) view.findViewById(R.id.phoneTextView);
        vehicalNumberTextView = (TextView) view.findViewById(R.id.vehicalNoTextView);
        aadharTextView = (TextView) view.findViewById(R.id.aadharTextView);
        vehicalTypeTextView = (TextView) view.findViewById(R.id.vehicalTypeTextView);
        circleImageView = (CircleImageView) view.findViewById(R.id.profile_image);
        materialToolbar = (MaterialToolbar) view.findViewById(R.id.materialToolbar);
        preferences = new Preferences(getContext());
        nameTextView.setText("" + preferences.getName());
        emailTextView.setText("" + preferences.getEmail());
        phoneTextView.setText("" + preferences.getPhone());
        aadharTextView.setText("" + preferences.getAadharNumber());
        vehicalNumberTextView.setText("" + preferences.getVehicalNum());
        vehicalTypeTextView.setText("" + preferences.getVehicalType());
        partnerIdTextView.setText("" + preferences.getPartnerId());
        getActivity().getActionBar();
        materialToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.logout) {
                    preferences.setToken("");
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
                return false;
            }
        });


        return view;
    }
}