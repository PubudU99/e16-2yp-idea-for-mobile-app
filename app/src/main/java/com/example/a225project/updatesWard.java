package com.example.a225project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class updatesWard extends AppCompatActivity {

    ImageView goBackBtn;
    ImageView submit;

    EditText uploadWardId,uploadWardName,uploadDoc,uploadNurse,uploadBeds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updates_ward);

        goBackBtn = findViewById(R.id.imageView38);
        uploadWardName=findViewById(R.id.editTextText31);
        uploadWardId = findViewById(R.id.editTextText32);
        uploadDoc = findViewById(R.id.editTextText33);
        uploadNurse = findViewById(R.id.editTextText34);
        uploadBeds =  findViewById(R.id.editTextText35);
        submit = findViewById(R.id.imageView37);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("ward");


        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getApplicationContext(), adminHome.class);
                startActivity(i2);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = uploadWardName.getText().toString().trim();
                String wardId = uploadWardId.getText().toString().trim();
                String docId = uploadDoc.getText().toString().trim();
                String nurseId = uploadNurse.getText().toString().trim();
                String beds = uploadBeds.getText().toString().trim();

                Ward ward = new Ward(name,wardId,docId,nurseId,beds);

                databaseReference.child(wardId).setValue(ward);
                Toast.makeText(getApplicationContext(), "Data added successfully", Toast.LENGTH_SHORT).show();

            }
        });
    }
}