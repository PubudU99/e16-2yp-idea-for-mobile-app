package com.example.a225project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class NextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        // Retrieve the ward ID from the Intent
        String wardId = getIntent().getStringExtra("wardId");

        // Print the ward ID
        System.out.println("Ward ID: " + wardId);
    }
}
