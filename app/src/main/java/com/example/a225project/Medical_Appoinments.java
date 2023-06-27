package com.example.a225project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class Medical_Appoinments extends AppCompatActivity {

    ArrayList<TodayAppointmentsModel> TodayAppointmentModels = new ArrayList<>();

    String[] DoctorNames ={"Kavindu bambaragama","Pubuduu madushith","Mahesha Madhushanka","Pasindu Rangana","Uthsata Wikramarachchi"};
    String[] DoctorTitle ={"Medical Specialist","Dental Sergen","Medical Specialist","Radiologist","Heart Specialist"};
    String[] AppointmentTypes = {"Medical checkup","Dental checkup","Medical checkup","X-ray test","Heart checkup"};
    String[] timePeriods = {"10.00AM - 11.00AM","10.00AM - 11.00AM","10.00AM - 11.00AM","10.00AM - 11.00AM","10.00AM - 11.00AM"};
    int[] ProfilePics ={R.drawable.dp1,R.drawable.dp2,R.drawable.dp3,R.drawable.dp4,R.drawable.dp5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_appoinments);
        RecyclerView recyclerView= findViewById(R.id.myRecyclerView);
        RecyclerView recyclerView2=findViewById(R.id.myRecyclerView2);
        RecyclerView recyclerView3=findViewById(R.id.myRecyclerView3);
        setUpTodayAppointmentModels();
        TodayAppointmentsAdapter adapter =new TodayAppointmentsAdapter(this,TodayAppointmentModels);
        recyclerView.setAdapter(adapter);
        recyclerView2.setAdapter(adapter);
        recyclerView3.setAdapter(adapter);
        ImageButton goBack =findViewById(R.id.goback_appt);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1= new Intent(getApplicationContext(),MainActivity3.class);
                startActivity(i1);
            }
        });
    }


    private  void setUpTodayAppointmentModels(){
        for(int i=0; i<DoctorNames.length;i++){
            TodayAppointmentModels.add(new TodayAppointmentsModel(AppointmentTypes[i],timePeriods[i],DoctorNames[i],DoctorTitle[i],ProfilePics[i]));

        }
    }
}