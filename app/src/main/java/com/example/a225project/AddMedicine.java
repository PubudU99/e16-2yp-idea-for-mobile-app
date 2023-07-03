package com.example.a225project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDateTime;

public class AddMedicine extends AppCompatActivity {

    ImageView add_more,finish, goBackBtn;
    EditText UploadMedicine,UploadWeight,UploadDose,UploadDays,UploadPerDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        goBackBtn = findViewById(R.id.imageView154);

        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getApplicationContext(), doctorAddPrescription.class);
                startActivity(i2);
            }
        });

        Intent intent=getIntent();
        String prescriptionId=intent.getStringExtra("prescriptionId");

        UploadMedicine=findViewById(R.id.editTextText2);
        UploadWeight=findViewById(R.id.editTextText3);
        UploadDose=findViewById(R.id.editTextText4);
        UploadDays=findViewById(R.id.editTextText6);
        UploadPerDay=findViewById(R.id.editTextText7);
        add_more=findViewById(R.id.imageView162);
        finish =findViewById(R.id.imageView163);



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("Medicine");

        add_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LocalDateTime date =null;

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    date = LocalDateTime.now();
                }

                String medicine =UploadMedicine.getText().toString().trim();
                String weight = UploadWeight.getText().toString().trim();
                String dose = UploadDose.getText().toString().trim();
                String days = UploadDays.getText().toString().trim();
                String perday =UploadPerDay.getText().toString().trim();

                MedicineModel medicinedata = new MedicineModel(prescriptionId,medicine,weight,dose,days,perday);
                String prescriptionKey = databaseReference.push().getKey();

                UploadMedicine.setText("");
                UploadDays.setText("");
                UploadWeight.setText("");
                UploadDose.setText("");
                UploadPerDay.setText("");

                databaseReference.child(prescriptionKey).setValue(medicinedata).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "Data added successfully", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalDateTime date = null;

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    date = LocalDateTime.now();
                }

                String medicine = UploadMedicine.getText().toString().trim();
                String weight = UploadWeight.getText().toString().trim();
                String dose = UploadDose.getText().toString().trim();
                String days = UploadDays.getText().toString().trim();
                String perday = UploadPerDay.getText().toString().trim();

                MedicineModel medicinedata = new MedicineModel(prescriptionId, medicine, weight, dose, days, perday);
                String prescriptionKey = databaseReference.push().getKey();
                databaseReference.child(prescriptionKey).setValue(medicinedata).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "Data added successfully", Toast.LENGTH_SHORT).show();
                        Intent i3 = new Intent(AddMedicine.this, DoctorView_PatientDetails.class);
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
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }


                });


            }
        });
    }
}