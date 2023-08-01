package com.example.emailauth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class MainActivity extends AppCompatActivity {

    private EditText emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.emailEditText);
        Button generateOTPButton = findViewById(R.id.generateOTPButton);

        generateOTPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                if (!email.isEmpty()) {
                    generateOTP(email);
                } else {
                    Toast.makeText(MainActivity.this, "Enter email address", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

private void generateOTP(final String email) {
    String otp = generateOTP();

    Runnable generateOTPRunnable = new Runnable() {
        @Override
        public void run() {
            EmailSender.sendEmail(email, "OTP Verification", "Your OTP is: " + otp);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, OTPVerificationActivity.class);
                    intent.putExtra("email", email);
                    intent.putExtra("otp", otp);
                    startActivity(intent);
                }
            });
        }
    };

    Executor executor = Executors.newSingleThreadExecutor();
    executor.execute(generateOTPRunnable);
}

    private String generateOTP() {
        // Generate a 6-digit OTP
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}
