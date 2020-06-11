package com.example.freshnexdelivery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    Button materialButton;
    TextView resetPassword;
    API_Interface api_interface;
    String phone, password;
    TextInputEditText textInputLayoutMobile, textInputLayoutPassword;
    private static final String TAG = "LoginActivity";
    Preferences preferences;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preferences = new Preferences(this);
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Processing...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        materialButton = (Button) findViewById(R.id.loginButton);
        resetPassword = (Button) findViewById(R.id.txt_forgotpassword);
        textInputLayoutMobile = (TextInputEditText) findViewById(R.id.ed_username);
        textInputLayoutPassword = (TextInputEditText) findViewById(R.id.ed_password);
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });
        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validation()) {
                    progressDialog.show();
                    api_interface = RetrofitClient.getClient().getApi();


                    api_interface.login(phone, password).enqueue(new Callback<LoginModel>() {
                            @Override
                            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                                //   try {

                                if (response != null && response.body() != null) {
                                    Log.d(TAG, "onResponse: " + response.body().getError());
                                    if (!response.body().getError()) {
                                        preferences.setToken(response.body().getData().getRememberToken());
                                        if (response.body().getData().getIsActivate() > 0) {
                                            preferences.setReset(true);
                                            preferences.setEmail(response.body().getData().getEmail());
                                            preferences.setPhone(response.body().getData().getMobile());
                                            preferences.setName(response.body().getData().getName());
                                            preferences.setPartnerId(response.body().getData().getPartnerId());
                                            preferences.setId(response.body().getData().getId());
                                            preferences.setStatus(response.body().getData().getStatus());
                                            preferences.setAadharNumber(response.body().getData().getAadharNo());
                                            preferences.setVehicalType(response.body().getData().getVehicleType());
                                            preferences.setVehicalNum(response.body().getData().getVehicleNo());
                                            preferences.setProfile(response.body().getData().getProfPicUrl());
                                            preferences.setPartnerId(response.body().getData().getPartnerId());
                                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            preferences.setReset(false);
                                            preferences.setEmail(response.body().getData().getEmail());
                                            preferences.setPhone(response.body().getData().getMobile());
                                            preferences.setName(response.body().getData().getName());
                                            preferences.setPartnerId(response.body().getData().getPartnerId());
                                            preferences.setId(response.body().getData().getId());
                                            preferences.setStatus(response.body().getData().getStatus());
                                            preferences.setProfile(response.body().getData().getProfPicUrl());
                                            preferences.setAadharNumber(response.body().getData().getAadharNo());
                                            preferences.setVehicalType(response.body().getData().getVehicleType());
                                            preferences.setVehicalNum(response.body().getData().getVehicleNo());
                                            preferences.setPartnerId(response.body().getData().getPartnerId());

                                            Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                        progressDialog.dismiss();

                                    } else {
                                        progressDialog.dismiss();
                                        Toast.makeText(LoginActivity.this, "Wrong Mobile or Password", Toast.LENGTH_SHORT).show();

                                    }
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(LoginActivity.this, "Wrong Mobile or Password", Toast.LENGTH_SHORT).show();
                                }
                                // JSONObject jsonObject = new JSONObject(response.body().toString());


//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }

                            }

                            @Override
                            public void onFailure(Call<LoginModel> call, Throwable t) {
                                Log.d(TAG, "onFailure: " + t.getMessage());
                                progressDialog.dismiss();
                            }
                        });


                }
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
            }
        });
    }

    public boolean validation() {
        phone = textInputLayoutMobile.getText().toString();
        password = textInputLayoutPassword.getText().toString();

        if (phone.isEmpty()) {
            textInputLayoutMobile.setError("Required");
            return false;
        }
        if (password.isEmpty()) {
            textInputLayoutPassword.setError("Required");
            return false;
        }
        return true;

    }
}