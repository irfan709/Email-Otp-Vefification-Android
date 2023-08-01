package com.example.emailauth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class OTPVerificationActivity extends AppCompatActivity {

    private EditText otpEditText;

    private String email;
    private String generatedOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);

        otpEditText = findViewById(R.id.otpEditText);
        Button verifyOTPButton = findViewById(R.id.verifyOTPButton);

        // Get the email and OTP from the previous activity
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        generatedOTP = intent.getStringExtra("otp");

        verifyOTPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredOTP = otpEditText.getText().toString().trim();
                if (!enteredOTP.isEmpty()) {
                    if (enteredOTP.equals(generatedOTP)) {
                        Toast.makeText(OTPVerificationActivity.this, "OTP verification successful", Toast.LENGTH_SHORT).show();
                        redirectToProfileActivity();
                    } else {
                        Toast.makeText(OTPVerificationActivity.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(OTPVerificationActivity.this, "Enter OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void redirectToProfileActivity() {
        Intent intent = new Intent(OTPVerificationActivity.this, ProfileActivity.class);
        intent.putExtra("email", email);
        startActivity(intent);
        finish();
    }
}

