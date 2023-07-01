package com.example.a225project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class doctorPatientDetails extends AppCompatActivity {
    ImageView reportHistory, prescriptionHistory, addPrescription;

    String adminID = "p_janaka";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_my_patients);

        reportHistory = findViewById(R.id.imageView185);
        prescriptionHistory = findViewById(R.id.imageView186);
        addPrescription = findViewById(R.id.imageView188);

        reportHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Medical_Report.class));

            }
        });

        prescriptionHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Patient_prescriptionView.class));

            }
        });

        addPrescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), nurseNewReports.class));

            }
        });
    }
}