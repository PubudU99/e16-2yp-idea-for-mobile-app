package com.example.a225project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Intent;
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
import android.widget.TextView;

public class caregiverHome extends AppCompatActivity {
// static String careGiverID;
static String patientIDValue;

    TextView name;

    String CareGiverID;

    ImageView viewPrescription, profImage,goBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregiver_home);

        goBack=findViewById(R.id.imageView151);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        // Patient Image

        Intent intent=getIntent();
        CareGiverID =intent.getStringExtra("username");

        name= findViewById(R.id.textView137);
        name.setText(CareGiverID);

        retrieveData();

        profImage = findViewById(R.id.imageViewPatient);

        profImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), EditProfile.class);
                i.putExtra("username", CareGiverID);
                startActivity(i);

            }
        });




        viewPrescription = findViewById(R.id.imageView186);

        viewPrescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i5= new Intent(getApplicationContext(),Patient_prescriptionView.class);
                String flag="C";
                i5.putExtra("Flag",flag);

                startActivity(i5);

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
        List<String> patientCareGiverList = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("patient");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                //getting patients


                for (DataSnapshot snapshot : datasnapshot.getChildren()){
                    String patientKey = snapshot.getKey();
                    patientIDList.add(patientKey);   //list of the patients

                    HashMap<String, Object> ma = (HashMap<String, Object>) snapshot.getValue();

                    patientNameList.add(ma.get("name").toString());      //list of nic
                    patientPhoneList.add(ma.get("phoneNumber").toString());      //list of admit date
                    patientWardList.add(ma.get("wardID").toString());      //list of nurse
                    patientBedList.add(ma.get("bedID").toString());        //list of ward ID
                    patientCareGiverList.add(ma.get("caregiverID").toString());        //list of ward ID

                }

                for (int i = 0; i < patientIDList.size(); i++) {

                    String patientCareGiver = patientCareGiverList.get(i);

                    if ( CareGiverID.equals(patientCareGiver) ) {
                        patientIDValue = patientIDList.get(i);

                        TextView patientID = findViewById(R.id.textView165);
                        patientID.setText(patientIDValue);

                        TextView patientName = findViewById(R.id.textView154);
                        patientName.setText(patientNameList.get(i));

                        TextView patientPhoneNumber = findViewById(R.id.textView167);
                        patientPhoneNumber.setText(patientPhoneList.get(i));

                        TextView patientWardID = findViewById(R.id.textView168);
                        patientWardID.setText(patientWardList.get(i));

                        TextView patientBedNumber = findViewById(R.id.textView169);
                        patientBedNumber.setText(patientBedList.get(i));

                        retrieveImage(); //calling image

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

        // Care Giver Image

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference().child("profile_pictures/"+CareGiverID+".jpeg");
        File localFile = new File(getCacheDir(), CareGiverID+".jpeg");
        storageRef.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());

                        ImageView imageView = findViewById(R.id.imageViewPatient);
                        imageView.setImageBitmap(bitmap);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle any errors that occur during image download
                    }
                });


        FirebaseStorage storage2 = FirebaseStorage.getInstance();
        System.out.println("The value of num is: " + patientIDValue);
        StorageReference storageRef2 = storage2.getReference().child("profile_pictures/"+patientIDValue+".jpeg");
        File localFile2 = new File(getCacheDir(), patientIDValue+".jpeg");
        storageRef2.getFile(localFile2)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Bitmap bitmap2 = BitmapFactory.decodeFile(localFile2.getAbsolutePath());

                        ImageView imageView2 = findViewById(R.id.imageView184);
                        imageView2.setImageBitmap(bitmap2);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle any errors that occur during image download
                    }
                });

    }

}



