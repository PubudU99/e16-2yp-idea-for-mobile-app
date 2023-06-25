package com.example.a225project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    TextView checkupType;
    TextView heartRate;
    TextView DocName;
    TextView DocTitle;
    TextView prescription;
    TextView Meal;
    TextView TimeDuration;
    ImageButton goBackButton;
    private  String text_Checkup_type="Medical Checkup- Routine";
    private String Doc_Name="Dr. Dhammike Kumara";
    private  String Doc_Title="Medical Specialist";
    private double bpmRate= 90.6;
    private  String tabletDetails="Aspirin: 2 tablets of 325 mg each\n" +
            "Amoxicillin: 1 capsule of 500 mg\n" +
            "Ibuprofen: 1 tablet of 200 mg\n" +
            "Levothyroxine: 1 tablet of 50 mcg\n" +
            "Metformin: 1 tablet of 500 mg";
    private String meal="After Dinner";
    private String timeDuration="9.00PM - 10.00PM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        heartRate=findViewById(R.id.heartRate);
        checkupType=findViewById(R.id.checkupType);
        goBackButton=findViewById(R.id.goBackbttn1);
        DocName=findViewById(R.id.docName);
        DocTitle=findViewById(R.id.docTitle);
        prescription=findViewById(R.id.prescription);
        checkupType.setText(text_Checkup_type);
        Meal=findViewById(R.id.mealID);
        TimeDuration=findViewById(R.id.timeID);
        heartRate.setText(bpmRate+" BPM");
        DocName.setText(Doc_Name);
        DocTitle.setText(Doc_Title);
        prescription.setText(tabletDetails);
        Meal.setText(meal);
        TimeDuration.setText(timeDuration);

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i2);
            }
        });


    }
}