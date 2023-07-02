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

public class admin_ward_inside_details
        extends AppCompatActivity {

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase
        FirebaseApp.initializeApp(this);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("wards");

        // Retrieve data from Firebase
        retrieveData();
    }

    private void retrieveData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Handle the retrieved data
                for (DataSnapshot wardSnapshot : dataSnapshot.getChildren()) {
                    String wardName = wardSnapshot.child("wardName").getValue(String.class);
                    String doctorIncharge = wardSnapshot.child("doctorIncharge").getValue(String.class);
                    String nurseIncharge = wardSnapshot.child("nurseIncharge").getValue(String.class);
                    String emergency = wardSnapshot.child("emergency").getValue(String.class);
                    int availableBeds = wardSnapshot.child("availableBeds").getValue(Integer.class);

                    // Update the UI with the retrieved data
                    updateUI(wardName, doctorIncharge, nurseIncharge, emergency, availableBeds);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any errors
            }
        });
    }

    private void updateUI(String wardName, String doctorIncharge, String nurseIncharge, String emergency, int availableBeds) {
        // Update the relevant TextViews and ImageView with the retrieved data
        TextView wardNameTextView = findViewById(R.id.textView123);
        wardNameTextView.setText(wardName);

        TextView doctorInchargeTextView = findViewById(R.id.textView110);
        doctorInchargeTextView.setText(doctorIncharge);

        TextView nurseInchargeTextView = findViewById(R.id.textView113);
        nurseInchargeTextView.setText(nurseIncharge);

        TextView emergencyTextView = findViewById(R.id.textView119);
        emergencyTextView.setText(emergency);

        TextView availableBedsTextView = findViewById(R.id.textView172);
        availableBedsTextView.setText(String.valueOf(availableBeds));
    }
}
