package com.example.a225project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class Doctor_HomePage extends AppCompatActivity {

    Calendar calendar;
    int day, month, year;

    TextView dateTxt;


    ArrayList<doctorTodayPatientsModel> doctorTodayPatientsModels= new ArrayList<>();
    String[] PatientNames={"Mahesha Madhushanka","Kavindu Bambaragama","Dinushika Abrew","Nikalshi Sepalika","Pasindu Rangana","Pubudu Madhushith","Uthsara wikramarachchi","Sapuni Nithya","Tharidi Sadewmi","John Seena"};
    String[] WardNo={"102D","133F","202A","402H","202D","102D","133F","202D","202A","102D"};
    String[] bedNo={"10","7","3","6","14","9","33","62","13","12"};
    String[] tTime={"10.00 AM","10.30 AM","11.00 AM","11.30 AM","12.00 PM","01.00 PM","02.00 PM","02.30 PM","03.00 PM","03.30 PM"};
    int[] patientProfilePics={R.drawable.pdp1,R.drawable.pdp2,R.drawable.pdp3,R.drawable.pdp4,R.drawable.pdp5,R.drawable.pdp6,R.drawable.pdp7,R.drawable.pdp8,R.drawable.pdp9,R.drawable.pdp10};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home_page);

        dateTxt=findViewById(R.id.txtdatedoctorhome);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        displayDate(year, month, day);



        RecyclerView recyclerView= findViewById(R.id.DoctorHomeRecyclerView);
        setUpDoctorTodayPatientsModels();
        doctorTodayPatientsAdapter adapter= new doctorTodayPatientsAdapter(this,doctorTodayPatientsModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }

    private void setUpDoctorTodayPatientsModels(){
        for(int i=0;i<PatientNames.length;i++){
            doctorTodayPatientsModels.add(new doctorTodayPatientsModel(PatientNames[i],WardNo[i],bedNo[i],tTime[i],patientProfilePics[i]));
        }

    }
    private void displayDate(int year, int month, int day) {
        month=month+1;
        String date = day+"/"+month+"/"+year;
        dateTxt.setText(date);
    }
}