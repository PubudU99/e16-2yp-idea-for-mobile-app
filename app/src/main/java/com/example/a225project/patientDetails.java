package com.example.a225project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class patientDetails extends AppCompatActivity {

    static String  patientID = nursePatientDetails.adminID;




    String[] PatientName ={"David", "Emma", "Frank", "Grace", "Henry", "Ivy", "Jack"};
    String[] PatientID ={"001","002","003","004","005","006","007"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);

    }

}