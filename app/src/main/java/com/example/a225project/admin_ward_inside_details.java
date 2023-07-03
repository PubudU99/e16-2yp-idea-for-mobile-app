package com.example.a225project;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class admin_ward_inside_details extends AppCompatActivity {


    TextView wardID, name, doctor, nurse, beds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_ward_inside_details);

        String wardId = getIntent().getStringExtra("wardId");

        // Print the ward ID
        System.out.println("Ward ID: " + wardId);

        wardID = findViewById(R.id.textView119);
        name = findViewById(R.id.textView109);
        doctor = findViewById(R.id.textView110);
        nurse = findViewById(R.id.textView113);
        beds = findViewById(R.id.textView172);


        FirebaseDatabase.getInstance().getReference().child("ward").child(wardId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            // Retrieve patient details
                            String wardID_s = dataSnapshot.child("id").getValue(String.class);
                            String name_s = dataSnapshot.child("name").getValue(String.class);
                            String doctor_s = dataSnapshot.child("doctor").getValue(String.class);
                            String nurse_s = dataSnapshot.child("nurse").getValue(String.class);
                            String beds_s = dataSnapshot.child("beds").getValue(String.class);


                            // Update the UI with patient details
                            wardID.setText(wardID_s);
                            name.setText(name_s);
                            doctor.setText(doctor_s);
                            nurse.setText(nurse_s);
                            beds.setText(beds_s);

                        } else {
                            Toast.makeText(admin_ward_inside_details.this, "Ward not found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(admin_ward_inside_details.this, "Failed to retrieve ward details", Toast.LENGTH_SHORT).show();
                    }
              });
}


}
