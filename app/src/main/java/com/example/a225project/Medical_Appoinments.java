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
    ArrayList<TodayAppointmentsModel> TomorrowAppointmentModels = new ArrayList<>();
    ArrayList<TodayAppointmentsModel> NextAppointmentModels = new ArrayList<>();
    //Today Data
    String[] DoctorNamesToday ={"Kavindu bambaragama","Pubuduu madushith",};
    String[] DoctorTitleToday ={"Medical Specialist","Dental Sergen"};
    String[] AppointmentTypesToday = {"Medical checkup","Dental checkup"};
    String[] timePeriodsToday = {"10.00AM - 11.00AM","10.00AM - 11.00AM"};
    int[] ProfilePicsToday ={R.drawable.dp4,R.drawable.dp5};

    //Tomorrow data
    String[] DoctorNamesTomorrow ={"Kavindu bambaragama","Pubuduu madushith","Mahesha Madhushanka"};
    String[] DoctorTitleTomorrow ={"Medical Specialist","Dental Sergen","Medical Specialist"};
    String[] AppointmentTypesTomorrow = {"Medical checkup","Dental checkup","Medical checkup"};
    String[] timePeriodsTomorrow = {"10.00AM - 11.00AM","10.00AM - 11.00AM","10.00AM - 11.00AM"};
    int[] ProfilePicsTomorrow ={R.drawable.dp3,R.drawable.dp4,R.drawable.dp5};

    //next 2-5 days
    String[] DoctorNamesNext ={"Kavindu bambaragama","Pubuduu madushith","Mahesha Madhushanka","Pasindu Rangana","Uthsata Wikramarachchi"};
    String[] DoctorTitleNext  ={"Medical Specialist","Dental Sergen","Medical Specialist","Radiologist","Heart Specialist"};
    String[] AppointmentTypesNext  = {"Medical checkup","Dental checkup","Medical checkup","X-ray test","Heart checkup"};
    String[] timePeriodsNext = {"10.00AM - 11.00AM","10.00AM - 11.00AM","10.00AM - 11.00AM","10.00AM - 11.00AM","10.00AM - 11.00AM"};
    int[] ProfilePicsNext ={R.drawable.dp1,R.drawable.dp2,R.drawable.dp3,R.drawable.dp4,R.drawable.dp5};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_appoinments);
        RecyclerView recyclerView= findViewById(R.id.myRecyclerView);
        RecyclerView recyclerView2=findViewById(R.id.myRecyclerView2);
        RecyclerView recyclerView3=findViewById(R.id.myRecyclerView3);
        setUpTodayAppointmentModels();
        setUpTomorrowAppointmentModels();
        setUpNextAppointmentModels();
        TodayAppointmentsAdapter adapterToday =new TodayAppointmentsAdapter(this,TodayAppointmentModels);
        TodayAppointmentsAdapter adapterTomorrow =new TodayAppointmentsAdapter(this,TomorrowAppointmentModels);
        TodayAppointmentsAdapter adapterNext =new TodayAppointmentsAdapter(this,NextAppointmentModels);
        recyclerView.setAdapter(adapterToday);
        recyclerView2.setAdapter(adapterTomorrow);
        recyclerView3.setAdapter(adapterNext);
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
        for(int i=0; i<DoctorNamesToday.length;i++){
            TodayAppointmentModels.add(new TodayAppointmentsModel(AppointmentTypesNext[i],timePeriodsNext[i],DoctorNamesNext[i],DoctorTitleNext[i],ProfilePicsNext[i]));

        }
    }
    private  void setUpTomorrowAppointmentModels(){
        for(int i=0; i<DoctorNamesTomorrow.length;i++){
            TomorrowAppointmentModels.add(new TodayAppointmentsModel(AppointmentTypesTomorrow[i],timePeriodsTomorrow[i],DoctorNamesTomorrow[i],DoctorTitleTomorrow[i],ProfilePicsTomorrow[i]));

        }
    }
    private  void setUpNextAppointmentModels(){
        for(int i=0; i<DoctorNamesNext.length;i++){
            NextAppointmentModels.add(new TodayAppointmentsModel(AppointmentTypesNext[i],timePeriodsNext[i],DoctorNamesNext[i],DoctorTitleNext[i],ProfilePicsNext[i]));

        }
    }
}