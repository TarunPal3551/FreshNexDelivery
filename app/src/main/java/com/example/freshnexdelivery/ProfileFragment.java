package com.example.freshnexdelivery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {
    TextView nameTextView, partnerIdTextView, emailTextView, phoneTextView, vehicalTypeTextView, vehicalNumberTextView, aadharTextView;
    CircleImageView circleImageView;
    Preferences preferences;
    MaterialToolbar materialToolbar;
    Switch statusSwitch;
    API_Interface api_interface;
    TextView textViewPending, textViewDelivered, textViewCancelled;
    private static final String TAG = "ProfileFragment";
    ArrayList<OrderData> orderDataArrayList = new ArrayList<>();
    int countPending = 0;
    int countDelivered = 0;
    int countCancelled = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        statusSwitch = (Switch) view.findViewById(R.id.statusSwitch);
        nameTextView = (TextView) view.findViewById(R.id.nameTextView);
        emailTextView = (TextView) view.findViewById(R.id.emailTextView);
        partnerIdTextView = (TextView) view.findViewById(R.id.partnerId);
        phoneTextView = (TextView) view.findViewById(R.id.phoneTextView);
        vehicalNumberTextView = (TextView) view.findViewById(R.id.vehicalNoTextView);
        aadharTextView = (TextView) view.findViewById(R.id.aadharTextView);
        vehicalTypeTextView = (TextView) view.findViewById(R.id.vehicalTypeTextView);
        circleImageView = (CircleImageView) view.findViewById(R.id.profile_image);
        materialToolbar = (MaterialToolbar) view.findViewById(R.id.materialToolbar);
        textViewPending = (TextView) view.findViewById(R.id.textViewPendingCount);
        textViewDelivered = (TextView) view.findViewById(R.id.textViewDeliveredCount);
        textViewCancelled = (TextView) view.findViewById(R.id.textViewCancelledCount);

        preferences = new Preferences(getContext());
        nameTextView.setText("" + preferences.getName());
        emailTextView.setText("" + preferences.getEmail());
        phoneTextView.setText("" + preferences.getPhone());
        aadharTextView.setText("" + preferences.getAadharNumber());
        vehicalNumberTextView.setText("" + preferences.getVehicalNum());
        vehicalTypeTextView.setText("" + preferences.getVehicalType());
        partnerIdTextView.setText("Partner Id " + preferences.getPartnerId());
        if (preferences.getStatus() > 0) {
            statusSwitch.setChecked(true);
        } else {
            statusSwitch.setChecked(false);
        }

        getActivity().getActionBar();
        materialToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.logout) {


                    final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(), R.style.AppBottomSheetDialogTheme);
                    bottomSheetDialog.setCancelable(true);
                    bottomSheetDialog.setCanceledOnTouchOutside(true);
                    bottomSheetDialog.setContentView(R.layout.order_cancel_dialog);
//                bottomSheetDialog.set(DialogFragment.STYLE_NO_FRAME, R.style.AppBottomSheetDialogTheme);
                    bottomSheetDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    Button yesButton = (Button) bottomSheetDialog.findViewById(R.id.yesButton);
                    Button noButton = (Button) bottomSheetDialog.findViewById(R.id.noButton);
                    TextView textView = (TextView) bottomSheetDialog.findViewById(R.id.textViewTittle);
                    textView.setText("Are you sure you want to Log Out?");
                    yesButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bottomSheetDialog.cancel();
                            preferences.setToken("");
                            Intent intent = new Intent(getContext(), LoginActivity.class);
                            startActivity(intent);
                            getActivity().finish();


                        }
                    });
                    noButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bottomSheetDialog.dismiss();
                        }
                    });
                    bottomSheetDialog.show();
                }
                return false;
            }
        });
        statusSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    changeAvailaibleStatus("Available");
                } else {
                    changeAvailaibleStatus("Unavailable");
                }
            }
        });

        Glide.with(getContext()).load(Constant.Image_Base_URL + preferences.getProfile()).thumbnail(Glide.with(getContext()).load(R.drawable.plroduct_place)).into(circleImageView);
        getOrderCount();

        return view;
    }

    public void getOrderCount() {
        countCancelled=0;
        countDelivered=0;
        countPending=0;
        api_interface = RetrofitClient.getClient().getApi();
        api_interface.getOrders(preferences.getToken()).enqueue(new Callback<OrderModel>() {
            @Override
            public void onResponse(Call<OrderModel> call, Response<OrderModel> response) {
                Log.d(TAG, "onResponse: " + response.body().getData().toString());
                if (!response.body().getError()) {
                    orderDataArrayList = response.body().getData();
                    for (int i = 0; i < orderDataArrayList.size(); i++) {
                        if (orderDataArrayList.get(i).getStatus().equals("cancelled")) {
                            countCancelled = countCancelled + 1;
                        } else if (orderDataArrayList.get(i).getStatus().equals("delivered")) {
                            countDelivered = countDelivered + 1;
                        } else {
                            countPending = countPending + 1;

                        }


                    }
                    textViewCancelled.setText("" + countCancelled);
                    textViewDelivered.setText("" + countDelivered);
                    textViewPending.setText("" + countPending);


                } else {
                    textViewCancelled.setText("" + 0);
                    textViewDelivered.setText("" + 0);
                    textViewPending.setText("" + 0);
                    //  Toast.makeText(getActivity(), "Token Expired...Login Again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderModel> call, Throwable t) {

            }
        });
    }

    public void changeAvailaibleStatus(final String status) {
        api_interface = RetrofitClient.getClient().getApi();
        api_interface.changeStatus(preferences.getToken(), status).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.d(TAG, "onResponse: " + response.body().toString());

                if (status.equals("Available")) {
                    statusSwitch.setChecked(true);
                    preferences.setStatus(1);
                } else {
                    statusSwitch.setChecked(false);
                    preferences.setStatus(0);
                }


            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }
}