package com.example.a225project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class doctorAddPrescription extends AppCompatActivity {

    ImageView submitButton;
    ImageView goBackBtn;

    String patientId = "get from Intent";
    String docId = "g";
    EditText Uploadillness,Uploadnote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_add_prescription);

        goBackBtn = findViewById(R.id.imageView156);
        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4 = new Intent(getApplicationContext(),Doctor_HomePage.class);
                i4.putExtra("username","d_james");
                startActivity(i4);

            }
        });


        submitButton =findViewById(R.id.imageView36);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(getApplicationContext(),AddMedicine.class);
                i3.putExtra("NAME",getIntent().getStringExtra("NAME"));
                i3.putExtra("WardNO",getIntent().getStringExtra("WardNO"));
                i3.putExtra("BedNO",getIntent().getStringExtra("BedNO"));
                i3.putExtra("TIME",getIntent().getStringExtra("TIME"));
                i3.putExtra("ID",getIntent().getStringExtra("ID"));
                i3.putExtra("ILLNESS",getIntent().getStringExtra("ILLNESS"));
                i3.putExtra("AGE",getIntent().getStringExtra("AGE"));
                i3.putExtra("DP",getIntent().getIntExtra("DP",0));
                startActivity(i3);
            }
        });




        }
}