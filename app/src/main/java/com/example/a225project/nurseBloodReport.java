package com.example.a225project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.DateTimeFormatterBuilder;
import org.threeten.bp.temporal.ChronoField;
import com.jakewharton.threetenabp.AndroidThreeTen;

import java.util.HashMap;

public class nurseBloodReport extends AppCompatActivity {

    EditText himoglobin, whileBlood, Platelet, Chorestrol, tryglisarides, ldl, hdl;
    ImageView submitBtn;
    String adminID = "p_john"; //  adminID = username... I used that please use it everywhere.

    // initialize user images

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_blood_report);
        AndroidThreeTen.init(this);

        himoglobin = findViewById(R.id.editTextText5);
        whileBlood = findViewById(R.id.editTextText8);
        Platelet = findViewById(R.id.editTextText9);
        Chorestrol = findViewById(R.id.editTextText10);
        tryglisarides = findViewById(R.id.editTextText11);
        ldl = findViewById(R.id.editTextText12);
        hdl = findViewById(R.id.editTextText13);

        submitBtn = findViewById(R.id.imageView94);

        ImageView goBack =findViewById(R.id.imageView208);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1= new Intent(getApplicationContext(),nurseNewReports.class);
                startActivity(i1);
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String himoglobin_s = himoglobin.getText().toString().trim();
                String whileBlood_s = whileBlood.getText().toString().trim();
                String Platelet_s = Platelet.getText().toString().trim();
                String Chorestrol_s = Chorestrol.getText().toString().trim();
                String tryglisarides_s = tryglisarides.getText().toString().trim();
                String ldl_s = ldl.getText().toString().trim();
                String hdl_s = hdl.getText().toString().trim();
                String date = datePicker();

                addReportToDatabase(adminID, himoglobin_s ,whileBlood_s, Platelet_s, Chorestrol_s , tryglisarides_s, ldl_s, hdl_s, date);

            }
        });


    }

    private void addReportToDatabase(String adminID, String himoglobin, String whileBlood, String Platelet, String Chorestrol, String tryglisarides, String ldl,String hdl,String date) {


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reportsRef = database.getReference("reportsblood");
        DatabaseReference userReportsRef = reportsRef.child(adminID);
        DatabaseReference dateReportsRef = userReportsRef.child(date);

        HashMap<String, Object> reportData = new HashMap<>();
        reportData.put("himoglobin", himoglobin);
        reportData.put("whileBlood", whileBlood);
        reportData.put("Platelet", Platelet);
        reportData.put("Chorestrol", Chorestrol);
        reportData.put("tryglisarides", tryglisarides);
        reportData.put("ldl", ldl);
        reportData.put("hdl", hdl);


        dateReportsRef.setValue(reportData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Data successfully added to the database
                        // Perform any necessary actions or show a success message
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Failed to add data to the database
                        // Handle the error appropriately
                    }
                });
    }

    private String datePicker() {
        LocalDate currentDate = LocalDate.now(org.threeten.bp.ZoneId.systemDefault());
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendValue(ChronoField.YEAR)
                .appendLiteral('-')
                .appendValue(ChronoField.MONTH_OF_YEAR)
                .appendLiteral('-')
                .appendValue(ChronoField.DAY_OF_MONTH)
                .toFormatter();
        String dateString = currentDate.format(formatter);
        return dateString;
    }

}