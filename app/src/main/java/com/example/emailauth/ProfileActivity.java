package com.example.emailauth;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView emailTextView = findViewById(R.id.emailTextView);

        // Get the email from the previous activity
        String email = getIntent().getStringExtra("email");

        emailTextView.setText(email);
    }
}
