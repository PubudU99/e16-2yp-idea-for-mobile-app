package com.example.a225project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class nursrHome extends AppCompatActivity {


    private RecyclerView recyclerView;
    private NursePatientRecyclerViewAdapter adapter;
    private List<Patient> patientList;
    ImageView  profilePic;
    ImageButton goBackBtn;
    TextView name;

    String nurseName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nursr_home);

        Intent intent=getIntent();
        String Username =intent.getStringExtra("username");


        name= findViewById(R.id.textView137);
        name.setText(Username);

        recyclerView = findViewById(R.id.MyRecyclerviewAdminDetails);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        patientList = new ArrayList<>();
        adapter = new NursePatientRecyclerViewAdapter(patientList, this);
        recyclerView.setAdapter(adapter);

        profilePic = findViewById(R.id.imageViewPatient);

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), EditProfile.class);
                i.putExtra("username", Username);
                startActivity(i);

            }
        });

        // Image details
        String imageName = Username+".jpeg"; // Replace with the image file name

        // Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference().child("profile_pictures").child(imageName); // Replace "images" with your folder name

        // Download the image and set it to the ImageView
        final long MAX_IMAGE_SIZE_BYTES = 1024 * 1024; // 1MB (adjust as needed)
        storageRef.getBytes(MAX_IMAGE_SIZE_BYTES)
                .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        // Decode the byte array into a Bitmap
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                        // Set the bitmap to the ImageView
                        profilePic.setImageBitmap(bitmap);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
                        // For example, set a placeholder image or show an error message
                        profilePic.setImageResource(R.drawable.a_laraa);
                    }
                });

        nurseName = Username;


        Query query = FirebaseDatabase.getInstance().getReference().child("patient")
                .orderByChild("nurse").equalTo(nurseName);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                patientList.clear();
                for (DataSnapshot patientSnapshot : dataSnapshot.getChildren()) {
                    Patient patient = patientSnapshot.getValue(Patient.class);
                    patientList.add(patient);
                }
                adapter.setPatientList(patientList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors
            }
        });

    }
}