package com.example.a225project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class nursePatientDetails extends AppCompatActivity {

    ImageView reportHistory, prescriptionHistory, newReport;

    static String adminID;
    TextView name, adminIDt, phoneNumber, wardID, bedNo;
    ImageView imageViewpatient,goBack;

    private void getIntend(){
        Intent intent = getIntent();
        String message = intent.getStringExtra("patientId");

        if (message != null) {
            adminID = message;
        }

        System.out.println(adminID);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_patient_details);

        imageViewpatient = findViewById(R.id.imageViewPatient);
        goBack=findViewById(R.id.imageView210);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getIntend();

        name = findViewById(R.id.textView154);
        adminIDt = findViewById(R.id.textView165);
        phoneNumber = findViewById(R.id.textView166);
        wardID = findViewById(R.id.textView167);
        bedNo = findViewById(R.id.textView168);

        String patientID = adminID;

        reportHistory = findViewById(R.id.imageView185);
        prescriptionHistory = findViewById(R.id.imageView186);
        newReport = findViewById(R.id.imageView188);


        // Retrieve patient details from Firebase Realtime Database
        FirebaseDatabase.getInstance().getReference().child("patient").child(patientID)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            // Retrieve patient details
                            String patientName = dataSnapshot.child("name").getValue(String.class);
                            String patientID = dataSnapshot.child("adminID").getValue(String.class);
                            String phoneNumber1 = dataSnapshot.child("phoneNumber").getValue(String.class);
                            String patientBed = dataSnapshot.child("bedID").getValue(String.class);
                            String patientWard = dataSnapshot.child("wardID").getValue(String.class);
                            String imageLink = dataSnapshot.child("image").getValue(String.class);

                            // Update the UI with patient details
                            name.setText(patientName);
                            adminIDt.setText(patientID);
                            phoneNumber.setText(phoneNumber1);
                            wardID.setText(patientWard);
                            bedNo.setText(patientBed);

                            // Load and display the patient's image
                            loadAndDisplayImage(imageLink); // <-- Add this line
                        } else {
                            Toast.makeText(nursePatientDetails.this, "Patient not found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(nursePatientDetails.this, "Failed to retrieve patient details", Toast.LENGTH_SHORT).show();
                    }
                });

        reportHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i5= new Intent(getApplicationContext(),Medical_Report.class);
                String flag="N";
                i5.putExtra("Flag",flag);
                i5.putExtra("username",adminID);

                startActivity(i5);

            }
        });

        prescriptionHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i5= new Intent(getApplicationContext(),Patient_prescriptionView.class);
                String flag="N";
                i5.putExtra("Flag",flag);

                startActivity(i5);
            }
        });

        newReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(nursePatientDetails.this, nurseNewReports.class);
                intent.putExtra("adminID", adminID);
                startActivity(intent);


            }
        });


    }

    private void loadAndDisplayImage(String imageLink) {
        if (TextUtils.isEmpty(imageLink)) {
            // Handle case where image link is empty
            // For example, display a default image or show an error message
            imageViewpatient.setImageResource(R.drawable.a_laraa);
            Toast.makeText(nursePatientDetails.this, "No image available", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl(imageLink);

        final long MAX_IMAGE_SIZE_BYTES = 1024 * 1024; // 1MB (adjust as needed)
        storageRef.getBytes(MAX_IMAGE_SIZE_BYTES)
                .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        // Decode the byte array into a Bitmap
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                        // Display the image in the circular ImageView
                        imageViewpatient.setImageBitmap(bitmap);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle failure to load the image
                        // For example, display a default image or show an error message
                        imageViewpatient.setImageResource(R.drawable.a_laraa);
                        Toast.makeText(nursePatientDetails.this, "Failed to load patient image", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
