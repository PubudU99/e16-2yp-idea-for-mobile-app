package com.example.a225project;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class admin_ward_inside_details extends AppCompatActivity {

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_ward_inside_details);

        String wardId = getIntent().getStringExtra("wardId");

        // Print the ward ID
        System.out.println("Ward ID: " + wardId);


        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference().child("ward").child(wardId);

        // Retrieve data from Firebase
        retrieveData();
    }

    private void retrieveData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Check if the ward exists
                if (dataSnapshot.exists()) {
                    // Get the ward details directly from the snapshot
                    String wardName = dataSnapshot.child("wardName").getValue(String.class);
                    String doctorIncharge = dataSnapshot.child("doctorIncharge").getValue(String.class);
                    String nurseIncharge = dataSnapshot.child("nurseIncharge").getValue(String.class);
                    String emergency = dataSnapshot.child("emergency").getValue(String.class);
                    String availableBeds = dataSnapshot.child("availableBeds").getValue(String.class);

                    // Update the UI with the retrieved data
                    //updateUI(wardName, doctorIncharge, nurseIncharge, emergency, availableBeds);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any errors
            }
        });
    }
}
