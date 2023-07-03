package com.example.a225project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class nurseNewReports extends AppCompatActivity {

    ImageView Blood, Urine, submitBtn;

    EditText heartRate, pressure, lungs, temperature;

    String adminID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_new_reports);


        heartRate = findViewById(R.id.editTextText27);
        pressure = findViewById(R.id.editTextText28);
        lungs = findViewById(R.id.editTextText29);
        temperature = findViewById(R.id.editTextText30);

        submitBtn =  findViewById(R.id.imageView176);

        Blood = findViewById(R.id.imageView140);
        Urine = findViewById(R.id.imageView141);

        ImageView goBack =findViewById(R.id.imageView209);

        Intent intent = getIntent();
        adminID = intent.getStringExtra("adminID");


        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String heartRate_s = heartRate.getText().toString().trim();
                String pressure_s = pressure.getText().toString().trim();
                String lungs_s = lungs.getText().toString().trim();
                String temperature_s = temperature.getText().toString().trim();

                updateUserInfo(heartRate_s,pressure_s,lungs_s,temperature_s,adminID);


            }
        });

        Blood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(nurseNewReports.this, nurseBloodReport.class);
                intent.putExtra("adminID", adminID);
                startActivity(intent);

            }
        });

        Urine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(nurseNewReports.this, nurseUrineReport.class);
                intent.putExtra("adminID", adminID);
                startActivity(intent);

            }
        });

    }


    private void updateUserInfo(String heartRate, String pressure, String lungs, String temperature, String adminID) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("patient").child(adminID);

        HashMap<String, Object> userData = new HashMap<>();
        userData.put("heartRate", heartRate);
        userData.put("pressure", pressure);
        userData.put("lungs", lungs);
        userData.put("temperature", temperature);

        usersRef.updateChildren(userData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // User data updated successfully
                        // Perform any necessary actions or show a success message
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Failed to update user data
                        // Handle the error appropriately
                    }
                });
    }

}