package com.example.a225project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DoctorView_PatientDetails extends AppCompatActivity {

    ImageView profilePic ;
    TextView Name ;
    TextView ID ;
    TextView Age ;
    TextView WardID ;
    TextView BedID ;
    TextView Illness;
    TextView AppointTime;

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

        profilePic.setImageResource(Integer.parseInt(getIntent().getStringExtra("DP")));
        Name.setText(getIntent().getStringExtra("NAME"));
        ID.setText(getIntent().getStringExtra("ID"));
        Age.setText(getIntent().getStringExtra("AGE"));
        WardID.setText(getIntent().getStringExtra("WardNO"));
        BedID.setText(getIntent().getStringExtra("BedNO"));
        Illness.setText(getIntent().getStringExtra("ILLNESS"));
        AppointTime.setText(getIntent().getStringExtra("TIME"));


    }
}