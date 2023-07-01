package com.example.a225project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class admin_viewAllNurses extends AppCompatActivity {

    ArrayList<admin_viewAallNurse_Model> adminViewAallNurseModels=new ArrayList<>();
    String[] PatientNames={"David", "Emma", "Frank", "Grace", "Henry", "Ivy", "Jack"};
    String[] patientID={"001","002","003","004","005","006","007"};

    RecyclerView nurseRecycle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_all_nurses);

        nurseRecycle=findViewById(R.id.MyRecyclerview);

        setup_adminViewAllNurseModels();

        admin_viewAllNurses_Adapter adapter = new admin_viewAllNurses_Adapter(this,adminViewAallNurseModels);
        nurseRecycle.setAdapter(adapter);
        nurseRecycle.setLayoutManager(new LinearLayoutManager(this));
    }
    private  void setup_adminViewAllNurseModels(){
        for(int i=0;i<PatientNames.length;i++){
            adminViewAallNurseModels.add(new admin_viewAallNurse_Model(PatientNames[i],patientID[i]));
        }
    }
}