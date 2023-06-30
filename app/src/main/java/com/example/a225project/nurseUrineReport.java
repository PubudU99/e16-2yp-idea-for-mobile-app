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

public class nurseUrineReport extends AppCompatActivity {

    EditText color, appearance, specific_gravity,ph, protein, glucose, ketones;
    ImageView submitBtn;
    String adminID = "p_john"; //  adminID = username... I used that please use it everywhere.

    // initialize user images

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_urine_report);
        AndroidThreeTen.init(this);

        color = findViewById(R.id.editTextText5);
        appearance = findViewById(R.id.editTextText8);
        specific_gravity = findViewById(R.id.editTextText9);
        ph = findViewById(R.id.editTextText10);
        protein = findViewById(R.id.editTextText11);
        glucose = findViewById(R.id.editTextText12);
        ketones = findViewById(R.id.editTextText13);

        submitBtn = findViewById(R.id.imageView94);

        ImageView goBack =findViewById(R.id.imageView211);

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

                String color_s = color.getText().toString().trim();
                String appearance_s = appearance.getText().toString().trim();
                String specific_gravity_s = specific_gravity.getText().toString().trim();
                String ph_s = ph.getText().toString().trim();
                String protein_s = protein.getText().toString().trim();
                String glucose_s = glucose.getText().toString().trim();
                String ketones_s = ketones.getText().toString().trim();
                String date = datePicker();

                addReportToDatabase(adminID, color_s ,appearance_s, specific_gravity_s, ph_s , protein_s, glucose_s, ketones_s, date);

            }
        });


    }

    private void addReportToDatabase(String adminID, String color, String appearance, String specific_gravity, String ph, String protein, String glucose,String ketones,String date) {


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reportsRef = database.getReference("urinereport");
        DatabaseReference userReportsRef = reportsRef.child(adminID);
        DatabaseReference dateReportsRef = userReportsRef.child(date);

        HashMap<String, Object> reportData = new HashMap<>();
        reportData.put("color", color);
        reportData.put("appearance", appearance);
        reportData.put("specific_gravity", specific_gravity);
        reportData.put("ph", ph);
        reportData.put("protein", protein);
        reportData.put("glucose", glucose);
        reportData.put("ketones", ketones);


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