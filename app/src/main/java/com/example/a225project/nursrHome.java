package com.example.a225project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class nursrHome extends AppCompatActivity {

    ImageView patientDetails, profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nursr_home);

        patientDetails = findViewById(R.id.imageView175);
        profilePic = findViewById(R.id.imageView195);

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(),EditProfile.class);
                startActivity(i1);
            }
        });

        patientDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), nurseNewReports.class));
            }
        });
    }
}