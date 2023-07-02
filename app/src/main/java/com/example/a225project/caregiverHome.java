package com.example.a225project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Intent;
import android.os.Bundle;
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
static String careGiverID;
static String patientIDValue;


    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        careGiverID = MainActivity.username;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregiver_home);
        retrieveData();

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

                    if ( careGiverID.equals(patientCareGiver) ) {
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
        StorageReference storageRef = storage.getReference().child("profile_pictures/"+careGiverID+".jpeg");
        File localFile = new File(getCacheDir(), careGiverID+".jpeg");
        storageRef.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());

                        ImageView imageView = findViewById(R.id.imageView150);
                        imageView.setImageBitmap(bitmap);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle any errors that occur during image download
                    }
                });

        // Patient Image

        Intent intent=getIntent();
        String Username =intent.getStringExtra("username");

        name= findViewById(R.id.textView137);
        name.setText(Username);
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



