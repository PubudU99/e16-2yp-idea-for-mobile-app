package com.example.a225project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class nursrHome extends AppCompatActivity {


    private RecyclerView recyclerView;
    private NursePatientRecyclerViewAdapter adapter;
    private List<Patient> patientList;
    ImageView patientDetails, profilePic;
    ImageButton goBackBtn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nursr_home);

        recyclerView = findViewById(R.id.MyRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


//        goBackBtn = findViewById(R.id.imageButton_nurse);
//        goBackBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i2 = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(i2);
//            }
//        });

        patientList = new ArrayList<>();
        adapter = new NursePatientRecyclerViewAdapter(patientList, this);
        recyclerView.setAdapter(adapter);

        profilePic = findViewById(R.id.imageView195);

        // Replace "nurseName" with the actual nurse's name you want to filter
        String nurseName = "n_sathya";

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