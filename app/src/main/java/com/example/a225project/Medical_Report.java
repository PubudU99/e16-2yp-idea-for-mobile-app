package com.example.a225project;

import androidx.annotation.NonNull;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;

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
            "Total Cholesterol:\n" +
            "Triglycerides:\n" +
            "LDL Cholesterol:\n" +
            "HDL Cholesterol:\n";
//    private String bloodvalues = "14.2 g/dL\n" +
//            "7,500 cells/mm³\n" +
//            "220,000 cells/mm³\n" +
//            "90 mg/dL\n" +
//            "180 mg/dL\n" +
//            "120 mg/dL\n" +
//            "110 mg/dL\n" +
//            "50 mg/dL\n";
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
        urineRepo = findViewById(R.id.urinerepo);
        urineValues = findViewById(R.id.urinevalues);
        urineRepo.setText(urinerepo);
        urineValues.setText(urinevalues);
        TxtDate = findViewById(R.id.txtDate);
//        bloodVlues.setText(bloodvalues);
        // create a calender instance
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);


        displayDate(year, month + 1, day);
        String username2 = getIntent().getStringExtra("username");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String selectedDate = TxtDate.getText().toString();
        DatabaseReference bloodValuesRef = database.getReference("reportsblood").child("a_bro").child(selectedDate);
        System.out.println(selectedDate);
        bloodValuesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Retrieve the data as a HashMap
                HashMap<String, Object> reportData = (HashMap<String, Object>) dataSnapshot.getValue();

                if (reportData != null) {
                    // Access individual values from the HashMap
                    String hemoglobin = (String) reportData.get("himoglobin");
                    String whileBlood = (String) reportData.get("whileBlood");
                    String Platelet = (String) reportData.get("Platelet");
                    String cholesterol = (String) reportData.get("Chorestrol");
                    String hey = (String) reportData.get("tryglisarides");
                    String ldl = (String) reportData.get("ldl");
                    String hdl = (String) reportData.get("hdl");

                    String blood_values = hemoglobin + "\n" +
                            whileBlood + "\n" +
                            Platelet + "\n" +
                            cholesterol + "\n" +
                            hey + "\n" +
                            ldl + "\n" +
                            hdl + "\n";
//                    System.out.println(blood_values);
                    bloodVlues.setText(blood_values);


                    // Do something with the retrieved data
                    // For example, update the UI or display the values in TextViews
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any errors that occur during data retrieval
            }
        });



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
                        String date = year+"-"+month+"-"+dayOfMonth;
                        TxtDate.setText(date);
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        String selectedDate = TxtDate.getText().toString();
                        DatabaseReference bloodValuesRef = database.getReference("reportsblood").child("a_bro").child(selectedDate);
                        System.out.println(selectedDate);
                        bloodValuesRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                // Retrieve the data as a HashMap
                                HashMap<String, Object> reportData = (HashMap<String, Object>) dataSnapshot.getValue();

                                if (reportData != null) {
                                    // Access individual values from the HashMap
                                    String hemoglobin = (String) reportData.get("himoglobin");
                                    String whileBlood = (String) reportData.get("whileBlood");
                                    String Platelet = (String) reportData.get("Platelet");
                                    String cholesterol = (String) reportData.get("Chorestrol");
                                    String hey = (String) reportData.get("tryglisarides");
                                    String ldl = (String) reportData.get("ldl");
                                    String hdl = (String) reportData.get("hdl");

                                    String blood_values = hemoglobin + "\n" +
                                            whileBlood + "\n" +
                                            Platelet + "\n" +
                                            cholesterol + "\n" +
                                            hey + "\n" +
                                            ldl + "\n" +
                                            hdl + "\n";
//                    System.out.println(blood_values);
                                    bloodVlues.setText(blood_values);


                                    // Do something with the retrieved data
                                    // For example, update the UI or display the values in TextViews
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                // Handle any errors that occur during data retrieval
                            }
                        });

                    }
                },year,month,day);

                datePikerDialog.show();
            }
        });
        setListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String date = year+"-"+month+"-"+dayOfMonth;
                TxtDate.setText(date);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                String selectedDate = TxtDate.getText().toString();
                DatabaseReference bloodValuesRef = database.getReference("reportsblood").child("a_bro").child(selectedDate);
                System.out.println(selectedDate);
                bloodValuesRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Retrieve the data as a HashMap
                        HashMap<String, Object> reportData = (HashMap<String, Object>) dataSnapshot.getValue();

                        if (reportData != null) {
                            // Access individual values from the HashMap
                            String hemoglobin = (String) reportData.get("himoglobin");
                            String whileBlood = (String) reportData.get("whileBlood");
                            String Platelet = (String) reportData.get("Platelet");
                            String cholesterol = (String) reportData.get("Chorestrol");
                            String hey = (String) reportData.get("tryglisarides");
                            String ldl = (String) reportData.get("ldl");
                            String hdl = (String) reportData.get("hdl");

                            String blood_values = hemoglobin + "\n" +
                                    whileBlood + "\n" +
                                    Platelet + "\n" +
                                    cholesterol + "\n" +
                                    hey + "\n" +
                                    ldl + "\n" +
                                    hdl + "\n";
//                    System.out.println(blood_values);
                            bloodVlues.setText(blood_values);


                            // Do something with the retrieved data
                            // For example, update the UI or display the values in TextViews
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle any errors that occur during data retrieval
                    }
                });

            }
        };

    }

    private void displayDate(int year, int month, int day) {
        // Display the date in text view
        String str =  year + "-" + month + "-" + day;
        TxtDate.setText(str);
    }
}