package com.example.freshnexdelivery;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.onesignal.OneSignal;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    final Fragment fragment1 = new PendingFragment();
    final Fragment fragment2 = new DeliveredFragment();
    final Fragment fragment3 = new NotificationFragment();
    final Fragment fragment4 = new ProfileFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;
    private static final String TAG = "MainActivity";
    Preferences preferences;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = new Preferences(this);
        OneSignal.setSubscription(true);
        OneSignal.setExternalUserId(preferences.getPhone());

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        LoadFragment(active);
        Log.d(TAG, "onCreate: " + preferences.getToken());


    }

    private boolean LoadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {

                case R.id.pending:
                    //  fm.beginTransaction().hide(selectedFragment).show(fragment1).commit();
                    active = fragment1;
                    break;

                case R.id.delivered:
                    //  fm.beginTransaction().hide(selectedFragment).show(fragment2).commit();

                    active = fragment2;
                    break;

                case R.id.notification:
                    //fm.beginTransaction().hide(selectedFragment).show(fragment3).commit();
                    active = fragment3;
                    break;
                case R.id.profile:
                    //  fm.beginTransaction().hide(selectedFragment).show(fragment4).commit();
                    active = fragment4;
                    break;

            }

            return LoadFragment(active);
        }
    };

    @Override
    public void onBackPressed() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this, R.style.AppBottomSheetDialogTheme);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.setContentView(R.layout.order_cancel_dialog);
//                bottomSheetDialog.set(DialogFragment.STYLE_NO_FRAME, R.style.AppBottomSheetDialogTheme);
        bottomSheetDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Button yesButton = (Button) bottomSheetDialog.findViewById(R.id.yesButton);
        Button noButton = (Button) bottomSheetDialog.findViewById(R.id.noButton);
        TextView textView = (TextView) bottomSheetDialog.findViewById(R.id.textViewTittle);
        textView.setText("Are you sure you want to Exit?");
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.cancel();
                MainActivity.super.onBackPressed();


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
}