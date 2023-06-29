package com.example.a225project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class adminHome extends AppCompatActivity {

    ImageView registartionImg;
    ImageView admissionImg;
    ImageView updateImg;
    ImageView viewImg;

    ImageView goBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        goBackBtn = findViewById(R.id.imageButton);

        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i2);
            }
        });

        registartionImg = findViewById(R.id.imageView63);

        registartionImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), registration.class));

            }
        });

        // go to admission
        admissionImg = findViewById(R.id.AdmissionImg);

        admissionImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), patientAdmittance.class));

            }
        });

        // go to update
        updateImg = findViewById(R.id.imageView65);

        updateImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), updatesWard.class));

            }
        });


        // go to view
        viewImg = findViewById(R.id.imageView66);

        viewImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), view.class));

            }
        });


    }
}