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
    String[] DoctorNamesToday ={"J.M. Mendis","I. Eragama"};
    String[] DoctorTitleToday ={"Rheumatology","Neurologist"};
    String[] AppointmentTypesToday = {"Medical checkup","Neuron checkup"};
    String[] timePeriodsToday = {"6.00PM","7.00PM"};
    int[] ProfilePicsToday ={R.drawable.dp1,R.drawable.dp4};

    //Tomorrow data
    String[] DoctorNamesTomorrow ={"R.K. James"};
    String[] DoctorTitleTomorrow ={"General Medicine"};
    String[] AppointmentTypesTomorrow = {"Dental checkup"};
    String[] timePeriodsTomorrow = {"11.00AM"};
    int[] ProfilePicsTomorrow ={R.drawable.dp3};

    //next 2-5 days
    String[] DoctorNamesNext ={"A.T. Kethaka","H.O. Shenal","V.U. Uthsara","K.D. Dinushika"};
    String[] DoctorTitleNext  ={"Pediatric","Orthopdics","Pathology","Radiologist","Heart Specialist"};
    String[] AppointmentTypesNext  = {"Medical checkup","x_rays","Eye checkup","X-ray test"};
    String[] timePeriodsNext = {"10.00AM","4.ppPM","6.00PM","8.00PM"};
    int[] ProfilePicsNext ={R.drawable.dp1,R.drawable.dp2,R.drawable.dp3,R.drawable.dp4};
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
            TodayAppointmentModels.add(new TodayAppointmentsModel(AppointmentTypesToday[i],timePeriodsToday[i],DoctorNamesToday[i],DoctorTitleToday[i],ProfilePicsToday[i]));

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