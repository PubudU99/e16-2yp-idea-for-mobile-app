package com.example.a225project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class Patient_prescriptionView extends AppCompatActivity {

    ArrayList <PrescripionModel> prescriptionmodels = new ArrayList<>();

    String[] TabletNames = {"Aspirin","Ibuprofen","Paracetamol","Cetirizine","Amoxicillin","Omeprazole","Loratadine"};
    String[] TabletWeights = {"100mg","200mg","500mg","10mg","250mg","20mg","5mg"};
    String[] amountsPerMeal = {"1 tablet","2 tablet","1 tablet","2 tablet","1 tablet","2 tablet","1 tablet"};
    String[] noOfDays = {"5 days","4 days","5 days","6 days","5 days","2 days","3 days"};
    String[] noOfMeals = {"3 per day","2 per day","1 per day","3 per day","2 per day","3 per day","1 per day"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_prescription_view);

        RecyclerView recyclerView= findViewById(R.id.prescriptionRecyclerView);
        setUpPrescriptionModels();
        Patient_presctiption_Adapter adapter = new Patient_presctiption_Adapter(this,prescriptionmodels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));

        ImageButton gobacktoHome=findViewById(R.id.imageButton2);
        gobacktoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1= new Intent(getApplicationContext(), MainActivity3.class);
                startActivity(i1);
            }
        });

    }
    private  void setUpPrescriptionModels(){
        for (int i=0;i<TabletNames.length;i++){
            prescriptionmodels.add(new PrescripionModel(TabletNames[i],TabletWeights[i],amountsPerMeal[i],noOfDays[i],noOfMeals[i]));
        }
    }

}