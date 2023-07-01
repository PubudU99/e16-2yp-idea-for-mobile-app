package com.example.a225project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
    ImageButton goBackBtn;

    String patientId = "get from Intent";
    String docId = "get from Intent";
    EditText Uploadillness,Uploadnote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_add_prescription);

        goBackBtn = findViewById(R.id.imageView156);
        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getApplicationContext(), Doctor_HomePage.class);
                startActivity(i2);
            }
        });

        Uploadillness =findViewById(R.id.editTextText2);
        Uploadnote = findViewById(R.id.editTextTextMultiLine);
        submitButton =findViewById(R.id.imageView170);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("prescriptions");

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalDateTime date =null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                     date= LocalDateTime.now();
                }
                String illness = Uploadillness.getText().toString().trim();
                String note = Uploadnote.getText().toString().trim();


                IllnessDetails details =new IllnessDetails(patientId,docId,illness,date,note);

                String prescriptionKey = databaseReference.push().getKey();

                databaseReference.child(prescriptionKey).setValue(details).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Intent intent=new Intent(doctorAddPrescription.this, AddMedicine.class );
                        intent.putExtra("prescriptionId",prescriptionKey);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Data added successfully", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

            }
        });




        }
}