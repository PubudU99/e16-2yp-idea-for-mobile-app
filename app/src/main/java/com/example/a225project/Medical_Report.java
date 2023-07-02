package com.example.a225project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
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

    TextView heart;//heart

    TextView pressure; //Pressure5

    TextView Lungs; //Lungs6

    TextView Tempurature; //Temperature8
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
    private String urinerepo = "Color:\n" +
            "Appearance:\n" +
            "Specific Gravity:\n" +
            "pH:\n" +
            "Protein:\n" +
            "Glucose:\n" +
            "Ketones:\n" ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_report);
        heart = findViewById(R.id.textView);
        pressure = findViewById(R.id.textView5);
        Lungs = findViewById(R.id.textView6);
        Tempurature = findViewById(R.id.textView8);
        bloodDisplay = findViewById(R.id.bloodRepo);
        bloodDisplay.setText(bloodrepo);
        bloodVlues = findViewById(R.id.bloodvalues);
        calenderBtn = findViewById(R.id.calander);
        urineRepo = findViewById(R.id.urinerepo);
        urineValues = findViewById(R.id.urinevalues);
        urineRepo.setText(urinerepo);
        //urineValues.setText(urinevalues);
        TxtDate = findViewById(R.id.txtDate);
//        bloodVlues.setText(bloodvalues);
        // create a calender instance
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        ImageView imagedescription= findViewById(R.id.imageView27);


        Intent intent = getIntent();
        String VarFlag=intent.getStringExtra("Flag");

        System.out.println(VarFlag);

        switch(VarFlag){
            case "P":
                Bitmap bitmapPatient = BitmapFactory.decodeResource(getResources(), R.drawable.healthstatus_of_patient);
                imagedescription.setImageBitmap(bitmapPatient);
                break;
            case "N":
                Bitmap bitmapNurse = BitmapFactory.decodeResource(getResources(), R.drawable.healthstatus_of_nursedoctor);
                imagedescription.setImageBitmap(bitmapNurse);
                break;
            case "D":
                bitmapNurse = BitmapFactory.decodeResource(getResources(), R.drawable.healthstatus_of_nursedoctor);
                imagedescription.setImageBitmap(bitmapNurse);
                break;
            default:
                break;
        }

        // intent got from previous page

        String flag = getIntent().getStringExtra("Flag");
        System.out.println(flag);


        displayDate(year, month + 1, day);
        String username = getIntent().getStringExtra("username");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String selectedDate = TxtDate.getText().toString();
        //System.out.println(username);
        DatabaseReference blockview = database.getReference("patient").child(username);
        DatabaseReference bloodValuesRef = database.getReference("reportsblood").child(username).child(selectedDate);
        System.out.println(selectedDate);
        DatabaseReference urineVlaueRef = database.getReference("urinereport").child(username).child(selectedDate);
        DatabaseReference patientRef = database.getReference("patient").child(username);
        patientRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Retrieve the patient data as a HashMap
                    HashMap<String, Object> patientData = (HashMap<String, Object>) dataSnapshot.getValue();

                    if (patientData != null) {
                        // Retrieve the specific values
                        String heartRate = (String) patientData.get("heartRate");
                        String lungs = (String) patientData.get("lungs");
                        String pressure2 = (String) patientData.get("pressure");
                        String temperature = (String) patientData.get("temperature");
                        System.out.println(heartRate);
                        // Set the values in the respective TextViews
                        heart.setText(heartRate);
                        Lungs.setText(lungs);
                        pressure.setText(pressure2);
                        Tempurature.setText(temperature);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any errors that occur during data retrieval
            }
        });

        urineVlaueRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Retrieve the data as a HashMap
                HashMap<String, Object> urineReportData = (HashMap<String, Object>) dataSnapshot.getValue();

                if (urineReportData != null) {
                    // Access individual values from the HashMap
                    String appearance = (String) urineReportData.get("appearance");
                    String color = (String) urineReportData.get("color");
                    String glucose = (String) urineReportData.get("glucose");
                    String ketones = (String) urineReportData.get("ketones");
                    String pH = (String) urineReportData.get("ph");
                    String protein = (String) urineReportData.get("protein");
                    String specificGravity = (String) urineReportData.get("specific_gravity");






                    // Create the urine report string
                    String urineReport =
                            appearance + "\n" + color + "\n" + glucose + "\n" + protein + "\n"+  pH + "\n" + ketones + "\n"+ specificGravity + "\n" ;

                    // Set the urine report text
                    urineValues.setText(urineReport);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any errors that occur during data retrieval
            }
        });

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
                i1.putExtra("username",username);
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
                        DatabaseReference bloodValuesRef = database.getReference("reportsblood").child(username).child(selectedDate);
                        DatabaseReference urineVlaueRef = database.getReference("urinereport").child(username).child(selectedDate);
                        urineVlaueRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                // Retrieve the data as a HashMap
                                HashMap<String, Object> urineReportData = (HashMap<String, Object>) dataSnapshot.getValue();

                                if (urineReportData != null) {
                                    // Access individual values from the HashMap
                                    String appearance = (String) urineReportData.get("appearance");
                                    String color = (String) urineReportData.get("color");
                                    String glucose = (String) urineReportData.get("glucose");
                                    String ketones = (String) urineReportData.get("ketones");
                                    String pH = (String) urineReportData.get("ph");
                                    String protein = (String) urineReportData.get("protein");
                                    String specificGravity = (String) urineReportData.get("specific_gravity");






                                    // Create the urine report string
                                    String urineReport =
                                            appearance + "\n" + color + "\n" + glucose + "\n" + protein + "\n"+  pH + "\n" + ketones + "\n"+ specificGravity + "\n" ;

                                    // Set the urine report text
                                    urineValues.setText(urineReport);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                // Handle any errors that occur during data retrieval
                            }
                        });
                        //System.out.println(selectedDate);
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

                String selectedDate = TxtDate.getText().toString();
                DatabaseReference bloodValuesRef = database.getReference("reportsblood").child(username).child(selectedDate);
                //System.out.println(selectedDate);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference urineVlaueRef = database.getReference("urinereport").child("p_john").child(selectedDate);
                urineVlaueRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Retrieve the data as a HashMap
                        HashMap<String, Object> urineReportData = (HashMap<String, Object>) dataSnapshot.getValue();

                        if (urineReportData != null) {
                            // Access individual values from the HashMap
                            String appearance = (String) urineReportData.get("appearance");
                            String color = (String) urineReportData.get("color");
                            String glucose = (String) urineReportData.get("glucose");
                            String ketones = (String) urineReportData.get("ketones");
                            String pH = (String) urineReportData.get("ph");
                            String protein = (String) urineReportData.get("protein");
                            String specificGravity = (String) urineReportData.get("specific_gravity");


                            // Create the urine report string
                            String urineReport =
                                    appearance + "\n" + color + "\n" + glucose + "\n" + protein + "\n"+  pH + "\n" + ketones + "\n"+ specificGravity + "\n" ;

                            // Set the urine report text
                            urineValues.setText(urineReport);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle any errors that occur during data retrieval
                    }
                });

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