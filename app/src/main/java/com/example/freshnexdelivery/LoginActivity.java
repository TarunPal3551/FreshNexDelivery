package com.example.freshnexdelivery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    MaterialButton materialButton;
    TextView resetPassword;
    API_Interface api_interface;
    String phone, password;
    TextInputLayout textInputLayoutMobile, textInputLayoutPassword;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        materialButton = (MaterialButton) findViewById(R.id.loginButton);
        resetPassword = (TextView) findViewById(R.id.txt_forgotpassword);
        textInputLayoutMobile = (TextInputLayout) findViewById(R.id.textInputLayoutMobile);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
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
                    api_interface = RetrofitClient.getClient().getApi();
                    Map<String, String> body = new HashMap<>();
                    body.put("mobile", phone);
                    body.put("password", password);
                    api_interface.login(body).enqueue(new Callback<JSONObject>() {
                        @Override
                        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                         //   try {
                                Log.d(TAG, "onResponse: " + response.body());
                               // JSONObject jsonObject = new JSONObject(response.body().toString());


//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }

                        }

                        @Override
                        public void onFailure(Call<JSONObject> call, Throwable t) {
                            Log.d(TAG, "onFailure: " + t.getMessage());
                        }
                    });
                }
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
            }
        });
    }

    public boolean validation() {
        phone = textInputLayoutMobile.getEditText().getText().toString();
        password = textInputLayoutPassword.getEditText().getText().toString();

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