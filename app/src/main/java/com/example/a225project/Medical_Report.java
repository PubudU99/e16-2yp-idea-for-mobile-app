package com.example.a225project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Calendar;

public class Medical_Report extends AppCompatActivity {

    TextView bloodDisplay;
    TextView bloodVlues;
    TextView urineRepo;
    TextView urineValues;
    TextView TxtDate;
    ImageButton goBackbtn;
    ImageButton calenderBtn;
    //DatePicker datePicker;
    DatePickerDialog.OnDateSetListener setListener;
    Calendar calendar;
    int day, month, year;


    private String bloodrepo = "Hemoglobin (Hb):\n" +
            "White Blood Cell Count:\n" +
            "Platelet Count:\n" +
            "Blood Glucose:\n" +
            "Total Cholesterol:\n" +
            "Triglycerides:\n" +
            "LDL Cholesterol:\n" +
            "HDL Cholesterol:\n";
    private String bloodvalues = "14.2 g/dL\n" +
            "7,500 cells/mm³\n" +
            "220,000 cells/mm³\n" +
            "90 mg/dL\n" +
            "180 mg/dL\n" +
            "120 mg/dL\n" +
            "110 mg/dL\n" +
            "50 mg/dL\n";
    private String urinerepo = "Color:\n" +
            "Appearance:\n" +
            "Specific Gravity:\n" +
            "pH:\n" +
            "Protein:\n" +
            "Glucose:\n" +
            "Ketones:\n" +
            "Blood:\n" +
            "Nitrite:\n" +
            "Leukocytes:\n" +
            "Bilirubin:\n" +
            "Urobilinogen:\n";
    private String urinevalues = "Pale yellow\n" +
            "Clear\n" +
            "1.020\n" +
            "6.0\n" +
            "Negative\n" +
            "Negative\n" +
            "Negative\n" +
            "Negative\n" +
            "Negative\n" +
            "Negative\n" +
            "Negative\n" +
            "Normal\n";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_report);
        bloodDisplay = findViewById(R.id.bloodRepo);
        bloodDisplay.setText(bloodrepo);
        bloodVlues = findViewById(R.id.bloodvalues);
        calenderBtn = findViewById(R.id.calander);
        bloodVlues.setText(bloodvalues);
        urineRepo = findViewById(R.id.urinerepo);
        urineValues = findViewById(R.id.urinevalues);
        urineRepo.setText(urinerepo);
        urineValues.setText(urinevalues);
        TxtDate = findViewById(R.id.txtDate);

        // create a calender instance
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        displayDate(year, month + 1, day);


        goBackbtn = findViewById(R.id.goBack);
        goBackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(getApplicationContext(), MainActivity3.class);
                startActivity(i1);

            }
        });
//        TxtDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatePickerDialog datepickerdialog = new DatePickerDialog(Medical_Report.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,setListener,year,month,day);
//                datepickerdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                datepickerdialog.show();
//            }
//        });
        calenderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePikerDialog = new DatePickerDialog(
                        Medical_Report.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month=month+1;
                        String date = dayOfMonth+"/"+month+"/"+year;
                        TxtDate.setText(date);
                    }
                },year,month,day);
                datePikerDialog.show();
            }
        });
        setListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String date = dayOfMonth+"/"+month+"/"+year;
                TxtDate.setText(date);

            }
        };

    }

    private void displayDate(int year, int month, int day) {
        // Display the date in text view
        String str = day + "/" + month + "/" + year;
        TxtDate.setText(str);
    }
}