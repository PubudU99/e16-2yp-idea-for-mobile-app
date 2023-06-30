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

    String[] TabletNames = {"Penadol","Penadol","Penadol","Penadol","Penadol","Penadol","Penadol"};
    String[] TabletWeights = {"350mg","350mg","350mg","350mg","350mg","350mg","350mg"};
    String[] amountsPerMeal={"x2","x2","x2","x2","x2","x2","x2"};
    String[] noOfDays={"3 Days","3 Days","3 Days","3 Days","3 Days","3 Days","3 Days"};
    String[] noOfMeals={"3 perDay","3 perDay","3 perDay","3 perDay","3 perDay","3 perDay","3 perDay"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_prescription_view);

        RecyclerView recyclerView= findViewById(R.id.prescriptionRecyclerView);
        setUpPrescriptionModels();
        Patient_presctiption_Adapter adapter = new Patient_presctiption_Adapter(this,prescriptionmodels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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