package com.example.a225project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Admin_viewAllPatients extends AppCompatActivity {

    ArrayList<admin_ViewAllPatients_Model> adminViewAllPatientsModels=new ArrayList<>();
    String[] PatientNames={"David", "Emma", "Frank", "Grace", "Henry", "Ivy", "Jack"};
    String[] patientID={"001","002","003","004","005","006","007"};

    RecyclerView patientRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_all_patients);

        patientRecycler=findViewById(R.id.doctorRecycl);

        setup_adminViewAllPatientsModels();

        admin_ViewAllPatients_Adapter adapter = new admin_ViewAllPatients_Adapter(this,adminViewAllPatientsModels);
        patientRecycler.setAdapter(adapter);
        patientRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    private  void setup_adminViewAllPatientsModels(){
        for(int i=0;i<PatientNames.length;i++){
            adminViewAllPatientsModels.add(new admin_ViewAllPatients_Model(PatientNames[i],patientID[i]));
        }
    }
}