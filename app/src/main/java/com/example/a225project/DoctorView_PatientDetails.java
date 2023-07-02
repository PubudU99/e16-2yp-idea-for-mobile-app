package com.example.a225project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DoctorView_PatientDetails extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view_patient_details);

        ImageView profilePic = findViewById(R.id.imageView231);
        TextView Name = findViewById(R.id.textView204);
        TextView ID = findViewById(R.id.textView205);
        TextView Age = findViewById(R.id.textView206);
        TextView WardID = findViewById(R.id.textView207);
        TextView BedID = findViewById(R.id.textView208);
        TextView Illness = findViewById(R.id.textView209);
        TextView AppointTime = findViewById(R.id.textView210);

        profilePic.setImageResource(getIntent().getIntExtra("DP",0));
        Name.setText(getIntent().getStringExtra("NAME"));
        ID.setText(getIntent().getStringExtra("ID"));
        Age.setText(getIntent().getStringExtra("AGE"));
        WardID.setText(getIntent().getStringExtra("WardNO"));
        BedID.setText(getIntent().getStringExtra("BedNO"));
        Illness.setText(getIntent().getStringExtra("ILLNESS"));
        AppointTime.setText(getIntent().getStringExtra("TIME"));

        ImageButton boBack= findViewById(R.id.imageButton8);
        ImageButton toReports= findViewById(R.id.imageButton9);
        ImageButton toPrescriptions=  findViewById(R.id.imageButton10);
        ImageButton toMakeAppointments= findViewById(R.id.imageButton11);

        toReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(),Medical_Report.class);
                String flag="N";
                i1.putExtra("Flag",flag);
                i1.putExtra("username","Default");
                startActivity(i1);
            }
        });

        toPrescriptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(getApplicationContext(),Patient_prescriptionView.class);
                String flag="N";
                i2.putExtra("Flag",flag);
                i2.putExtra("username","Default");
                startActivity(i2);

            }
        });

        toMakeAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),doctorNewAdmission.class));
            }
        });

        boBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Doctor_HomePage.class));
            }
        });




    }
}