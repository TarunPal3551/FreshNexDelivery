package com.example.freshnexdelivery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordActivity extends AppCompatActivity {
    TextInputEditText textInputLayoutOldPassword, textInputLayoutNewPassword, textInputLayoutConfirmPassword;
    String oldPassword, newPassword, confirmPassword;
    API_Interface api_interface;
    Preferences preferences;
    private static final String TAG = "ResetPasswordActivity";
    Button resetButton;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        progressDialog = new ProgressDialog(ResetPasswordActivity.this);
        progressDialog.setMessage("Processing...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        preferences = new Preferences(this);
        textInputLayoutOldPassword = (TextInputEditText) findViewById(R.id.ed_username);
        textInputLayoutNewPassword = (TextInputEditText) findViewById(R.id.ed_password);
        textInputLayoutConfirmPassword = (TextInputEditText) findViewById(R.id.ed_confirmpassword);
        resetButton = (Button) findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    resetPassword();
                }
            }
        });
    }

    public void resetPassword() {
        progressDialog.show();
        api_interface = RetrofitClient.getClient().getApi();
        api_interface.resetPassword(preferences.getToken(), oldPassword, newPassword).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                progressDialog.dismiss();
                Log.d(TAG, "onResponse: " + response);
                if (response.code() == 200) {
                    Log.d(TAG, "onResponse: " + response.body());
                    preferences.setReset(true);
                    Intent intent = new Intent(ResetPasswordActivity.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(ResetPasswordActivity.this, "Password Reset Successful", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(ResetPasswordActivity.this, "Wrong Old Password", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                progressDialog.dismiss();
            }
        });

    }

    public boolean validate() {
        oldPassword = textInputLayoutOldPassword.getText().toString();
        newPassword = textInputLayoutNewPassword.getText().toString();
        confirmPassword = textInputLayoutConfirmPassword.getText().toString();
        if (oldPassword.isEmpty()) {
            textInputLayoutOldPassword.setError("Required");
            return false;
        }
        if (newPassword.isEmpty()) {
            textInputLayoutNewPassword.setError("Required");
            return false;
        }
        if (confirmPassword.isEmpty()) {
            textInputLayoutConfirmPassword.setError("Required");
            return false;
        }

        if (!newPassword.equals(confirmPassword)) {
            textInputLayoutConfirmPassword.setError("Password Not Matched");
            Toast.makeText(this, "New Password and Confirm Password Not Matched", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}