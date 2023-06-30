package com.example.a225project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;



public class Doctor_HomePage extends AppCompatActivity {

    Calendar calendar;
    int day, month, year;

    TextView dateTxt;

    ImageView toAppoin;

    ////////// lists related to patients ////////////////
    List<String> PatientList = new ArrayList<>();
    List<String> nicList = new ArrayList<>();
    List<String> admitDateList = new ArrayList<>();
    List<String> bedIDList = new ArrayList<>();
    List<String> nurseList = new ArrayList<>();
    List<String> wardIDList = new ArrayList<>();



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

        retrieveData();
        displayDate(year, month, day);


        toAppoin =  findViewById(R.id.imageView199);


        toAppoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), doctorNewAdmission.class));
                System.out.println("hello");
            }
        });



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


    ////retrievieng data to a list
    public void retrieveData()
    {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("patient");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {


                //getting patients
                for (DataSnapshot snapshot : datasnapshot.getChildren()){
                    String patientKey = snapshot.getKey();
                    PatientList.add(patientKey);   //list of the patients

                    HashMap<String, Object> ma = (HashMap<String, Object>) snapshot.getValue();

                    nicList.add(ma.get("nic").toString());      //list of nic
                    //admitDateList.add(ma.get("admitDate").toString());      //list of admit date
                    //bedIDList.add(ma.get("bedID").toString());      //list of bed ID
                    //nurseList.add(ma.get("nurse").toString());      //list of nurse
                    //wardIDList.add(ma.get("wardID").toString());        //list of ward ID

                }

                ///printing to check correctness

                System.out.println(PatientList);
                System.out.println(nicList);
                System.out.println(admitDateList);
                System.out.println(bedIDList);
                System.out.println(nurseList);
                System.out.println(wardIDList);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



}