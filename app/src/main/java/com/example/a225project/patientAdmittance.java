package com.example.a225project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.DateTimeFormatterBuilder;
import org.threeten.bp.temporal.ChronoField;

import java.util.HashMap;

public class patientAdmittance extends AppCompatActivity {

    ImageView admitBtn, goBackBtn;
    EditText patientNIC, caregiverID, nurse, wardID, bedID, notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_admittance);
        AndroidThreeTen.init(this);

        //go back function
        goBackBtn = findViewById(R.id.imageView161);
        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getApplicationContext(), adminHome.class);
                startActivity(i2);
            }
        });

        patientNIC = findViewById(R.id.editTextText24);
        caregiverID = findViewById(R.id.editTextText38);
        nurse = findViewById(R.id.editTextText26);
        wardID = findViewById(R.id.editTextText27);
        bedID = findViewById(R.id.editTextText28);
        notes = findViewById(R.id.editTextTextMultiLine2);

        admitBtn = findViewById(R.id.imageView160);

        admitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String patientNIC_s = patientNIC.getText().toString().trim();
                String caregiverID_s  = caregiverID.getText().toString().trim();
                String nurse_s  = nurse.getText().toString().trim();
                String wardID_s  = wardID.getText().toString().trim();
                String bedID_s  = bedID.getText().toString().trim();
                String notes_s  = notes.getText().toString().trim();
                String date = datePicker();

                searchAndUpdateUser(patientNIC_s, caregiverID_s, nurse_s, wardID_s, bedID_s, date);
                updateNumberOfBeds(wardID_s);

            }
        });

    }


    private void searchAndUpdateUser(String patientNIC, String caregiverID, String nurse,String wardID,String bedID,String date) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("patient");

        Query query = usersRef.orderByChild("nic").equalTo(patientNIC);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String userId = userSnapshot.getKey();
                    DatabaseReference userRef = usersRef.child(userId);

                    // Update the specific fields for the user
                    HashMap<String, Object> userData = new HashMap<>();
                    userData.put("patientNIC", patientNIC);
                    userData.put("caregiverID", caregiverID);
                    userData.put("nurse", nurse);
                    userData.put("wardID", wardID);
                    userData.put("bedID", bedID);
                    userData.put("date", date);
                    // Add more attributes if needed

                    userRef.updateChildren(userData)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(patientAdmittance.this, "success",
                                            Toast.LENGTH_SHORT).show();
                                    // User data updated successfully
                                    // Perform any necessary actions or show a success message
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(patientAdmittance.this, "failed",
                                            Toast.LENGTH_SHORT).show();
                                    // Failed to update user data
                                    // Handle the error appropriately
                                }
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(patientAdmittance.this, "Please fill the all fields",
                        Toast.LENGTH_SHORT).show();
                // Error occurred while searching for the user
                // Handle the error appropriately
            }
        });
    }

    private void updateNumberOfBeds(String wardID) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("ward");

        DatabaseReference wardRef = usersRef.child(wardID);

        wardRef.child("beds").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String bedCountStr = dataSnapshot.getValue(String.class);
                if (bedCountStr != null) {
                    int bedCount = Integer.parseInt(bedCountStr);
                    if (bedCount > 0) {
                        bedCount--; // Decrease the bed count by 1
                        wardRef.child("beds").setValue(String.valueOf(bedCount))
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // Bed count updated successfully
                                        Toast.makeText(patientAdmittance.this, "Bed count updated", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Failed to update bed count
                                        Toast.makeText(patientAdmittance.this, "Failed to update bed count", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        // No available beds in the ward
                        Toast.makeText(patientAdmittance.this, "No available beds in the ward", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Error occurred while retrieving bed count
                Toast.makeText(patientAdmittance.this, "Failed to retrieve bed count", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private String datePicker() {
        LocalDate currentDate = LocalDate.now(org.threeten.bp.ZoneId.systemDefault());
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendValue(ChronoField.YEAR)
                .appendLiteral('-')
                .appendValue(ChronoField.MONTH_OF_YEAR)
                .appendLiteral('-')
                .appendValue(ChronoField.DAY_OF_MONTH)
                .toFormatter();
        String dateString = currentDate.format(formatter);
        return dateString;
    }

    private void clear(EditText patientNIC, EditText caregiverID, EditText nurse, EditText wardID, EditText bedID, EditText notes){
        patientNIC.setText("");
        caregiverID.setText("");
        nurse.setText("");
        wardID.setText("");
        bedID.setText("");
        notes.setText("");
    }

}