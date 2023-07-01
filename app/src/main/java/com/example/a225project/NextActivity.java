package com.example.a225project;


import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class NextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        // Get the patient ID from the intent
        Intent intent = getIntent();
        if (intent != null) {
            String patientId = intent.getStringExtra("patientId");
            if (patientId != null) {
                // Print the patient ID
                System.out.println("Patient ID: " + patientId);
            }
        }
    }
}
