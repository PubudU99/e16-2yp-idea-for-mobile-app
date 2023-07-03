package com.example.a225project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class doctorNewAdmission extends AppCompatActivity {


    EditText recommendedDoctor,date, time, notes;

    ImageView submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_new_admission);


        recommendedDoctor = findViewById(R.id.editTextText24);
        date = findViewById(R.id.editTextText25);
        time = findViewById(R.id.editTextText26);
        notes = findViewById(R.id.editTextTextMultiLine2);

        submitButton = findViewById(R.id.imageView3);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Assign to variables before register
                String recommendedDoctor_s = (recommendedDoctor.getText().toString().trim()).toLowerCase();
                String date_s = date.getText().toString().trim();
                String time_s = time.getText().toString().trim();
                String notes_s = notes.getText().toString().trim();
                String adminID_s = "HSAD";
                String doctorID_s = "HSSAD";

                storeUserData(recommendedDoctor_s, date_s, time_s, notes_s, adminID_s, doctorID_s);


            }
        });

    }

    public void storeUserData(String recommendedDoctor, String date, String time ,String notes, String adminID, String doctorID) {

        Appoinment appoinment = new Appoinment(recommendedDoctor, date, time, notes, adminID, doctorID);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("appoinment");

        DatabaseReference newUserRef = usersRef.push();
        String userId = newUserRef.getKey();

        newUserRef.setValue(appoinment);
    }
}