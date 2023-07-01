package com.example.a225project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class nursePatientDetails extends AppCompatActivity {

    ImageView reportHistory, prescriptionHistory, newReport;
    StorageReference storageReference;



    String adminID = "p_boss";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_patient_details);

        reportHistory = findViewById(R.id.imageView185);
        prescriptionHistory = findViewById(R.id.imageView186);
        newReport = findViewById(R.id.imageView188);
        retrieveData();

        reportHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Medical_Report.class));

            }
        });

        prescriptionHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Patient_prescriptionView.class));

            }
        });

        newReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), nurseNewReports.class));

            }
        });
    }







    ////retrievieng data to a list
    public void retrieveData()
    {
        ////////// lists related to patients ////////////////
        List<String> patientIDList = new ArrayList<>();
        List<String> patientNameList = new ArrayList<>();
        List<String> patientPhoneList = new ArrayList<>();
        List<String> patientWardList = new ArrayList<>();
        List<String> patientBedList = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("patient");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                //getting patients
                retrieveImage(); //calling image

                for (DataSnapshot snapshot : datasnapshot.getChildren()){
                    String patientKey = snapshot.getKey();
                    patientIDList.add(patientKey);   //list of the patients

                    HashMap<String, Object> ma = (HashMap<String, Object>) snapshot.getValue();

                    patientNameList.add(ma.get("name").toString());      //list of nic
                    patientPhoneList.add(ma.get("phoneNumber").toString());      //list of admit date
                    patientWardList.add(ma.get("wardID").toString());      //list of nurse
                    patientBedList.add(ma.get("bedID").toString());        //list of ward ID

                }

                for (int i = 0; i < patientIDList.size(); i++) {
                    String patient = patientIDList.get(i);

                    if ( adminID.equals(patient) ) {
                        TextView patientID = findViewById(R.id.textView165);
                        patientID.setText(patient);

                        TextView patientName = findViewById(R.id.textView154);
                        patientName.setText(patientNameList.get(i));

                        TextView patientPhoneNumber = findViewById(R.id.textView167);
                        patientPhoneNumber.setText(patientPhoneList.get(i));

                        TextView patientWardID = findViewById(R.id.textView168);
                        patientWardID.setText(patientWardList.get(i));

                        TextView patientBedNumber = findViewById(R.id.textView169);
                        patientBedNumber.setText(patientBedList.get(i));

                    }



                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    //retrieving the image
    public void retrieveImage (){
        // Create a FirebaseStorage instance

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference().child("profile_pictures/"+adminID+".jpeg");
        File localFile = new File(getCacheDir(), adminID+".jpg");
        storageRef.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());

                        ImageView imageView = findViewById(R.id.imageView184);
                        imageView.setImageBitmap(bitmap);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle any errors that occur during image download
                        Toast.makeText(nursePatientDetails.this, "Image download failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }



}