package com.example.freshnexdelivery;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 1000;
    Preferences preferences;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView=(ImageView)findViewById(R.id.splashImage);

        preferences = new Preferences(this);
        if (preferences.getToken().equals("")) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, SPLASH_TIME_OUT);
        } else {
            if (preferences.getReset()) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, SPLASH_TIME_OUT);
            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, SPLASH_TIME_OUT);
            }

        }

    }
}