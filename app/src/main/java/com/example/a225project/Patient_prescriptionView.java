package com.example.a225project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
    }
// need to construct
//    private  void setUpPrescriptionModels(){
//        for (int i=0;i<TabletNames.length;i++){
//            prescriptionmodels.add(new )
//        }
//    }

}