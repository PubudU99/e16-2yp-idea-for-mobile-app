package com.example.a225project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class admin_viewAllDoctors extends AppCompatActivity {

    ArrayList<admin_viewAllDoctors_Model> admin_viewAllDoctors_models=new ArrayList<>();
    String[] PatientNames={"David", "Emma", "Frank", "Grace", "Henry", "Ivy", "Jack"};
    String[] patientID={"001","002","003","004","005","006","007"};

    RecyclerView doctorRecycl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_all_doctors);

        doctorRecycl=findViewById(R.id.doctorRecycl);

        setup_adminViewAllDoctorsModels();

        admin_viewAllDoctors_Adapter adapter = new admin_viewAllDoctors_Adapter(this,admin_viewAllDoctors_models);
        doctorRecycl.setAdapter(adapter);
        doctorRecycl.setLayoutManager(new LinearLayoutManager(this));
    }
    private  void setup_adminViewAllDoctorsModels(){
        for(int i=0;i<PatientNames.length;i++){
            admin_viewAllDoctors_models.add(new admin_viewAllDoctors_Model(PatientNames[i],patientID[i]));
        }
    }
}
