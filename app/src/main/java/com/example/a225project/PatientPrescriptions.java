package com.example.a225project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class PatientPrescriptions extends AppCompatActivity {

    ArrayList<PrescriptionModel> prescriptionModels =new ArrayList<>();

    String[] TabletNames={"Penadol","Sytrazine","Asprin","Donpitidon","Amoxillin","Vitamin C","zinnat"};
    String[] TabletWeights={"350mg","50mg","250mg","300mg","10mg","200mg","300mg"};
    String[] TabletAmmounts={"x2","x1","x1","x1","x2","x1","x2"};
    String[] NoOfDays={"3 Days","2 Days","4 Days","3 Days","3 Days","2 Days","3 Days"};
    String[] TermsPerDay={"3 PerDay","3 PerDay","3 PerDay","3 PerDay","3 PerDay","3 PerDay","3 PerDay"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_prescriptions);
    }
    private void setPrescriptionModels(){
        for(int i=0;i<TabletNames.length;i++){
            //need to add from here
            //prescriptionModels.add()
        }
    }
}