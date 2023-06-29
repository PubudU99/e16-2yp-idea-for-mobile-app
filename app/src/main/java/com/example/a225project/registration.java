package com.example.a225project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class registration extends AppCompatActivity {

    ImageView patientReg, doctorReg, nurseReg, caregiverReg, adminReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        patientReg = findViewById(R.id.patientImg);

        patientReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), patientReg.class));

            }
        });

        doctorReg = findViewById(R.id.doctorImg);

        doctorReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), doctorReg.class));

            }
        });

        nurseReg = findViewById(R.id.nurseImg);

        nurseReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), regNurse.class));

            }
        });

        caregiverReg = findViewById(R.id.caregiverImg);

        caregiverReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), caregiverReg.class));

            }
        });

        adminReg = findViewById(R.id.adminImg);

        adminReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), adminReg.class));

            }
        });
    }


}