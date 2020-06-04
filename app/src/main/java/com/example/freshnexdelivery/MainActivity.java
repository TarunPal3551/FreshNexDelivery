package com.example.freshnexdelivery;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    final Fragment fragment1 = new PendingFragment();
    final Fragment fragment2 = new DeliveredFragment();
    final Fragment fragment3 = new NotificationFragment();
    final Fragment fragment4 = new ProfileFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        LoadFragment(active);


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

}