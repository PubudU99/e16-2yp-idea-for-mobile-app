package com.example.a225project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectReport extends AppCompatActivity {

    Button bloodBtn, urineBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_report);

        bloodBtn = findViewById(R.id.blood);
        urineBtn = findViewById(R.id.urine);

        bloodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), nurseBloodReport.class));

            }
        });


        urineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), nurseUrineReport.class));

            }
        });

    }
}