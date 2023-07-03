package com.example.a225project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class adminHome extends AppCompatActivity {

    ImageView registartionImg;
    ImageView admissionImg;
    ImageView updateImg;
    ImageView viewImg;
    ImageButton goBackBtn;
    TextView name;

    ImageView profPic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        goBackBtn = findViewById(R.id.imageButton);
        Intent intent=getIntent();
        String Username =intent.getStringExtra("username");

        name= findViewById(R.id.textView24);
        name.setText(Username);

        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i2);
            }
        });


        profPic = findViewById(R.id.imageViewPatient);

        profPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), EditProfile.class);
                i.putExtra("username", Username);
                startActivity(i);

            }
        });

        // Image details
        String imageName = Username+".jpeg"; // Replace with the image file name

        // Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference().child("profile_pictures").child(imageName); // Replace "images" with your folder name

        // Download the image and set it to the ImageView
        final long MAX_IMAGE_SIZE_BYTES = 1024 * 1024; // 1MB (adjust as needed)
        storageRef.getBytes(MAX_IMAGE_SIZE_BYTES)
                .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        // Decode the byte array into a Bitmap
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                        // Set the bitmap to the ImageView
                        profPic.setImageBitmap(bitmap);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
                        // For example, set a placeholder image or show an error message
                        profPic.setImageResource(R.drawable.a_laraa);
                    }
                });


        registartionImg = findViewById(R.id.imageView63);

        registartionImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), registration.class));

            }
        });

        // go to admission
        admissionImg = findViewById(R.id.AdmissionImg);

        admissionImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), patientAdmittance.class));

            }
        });

        // go to update
        updateImg = findViewById(R.id.imageView65);

        updateImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), updatesWard.class));

            }
        });


        // go to view
        viewImg = findViewById(R.id.imageView66);

        viewImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), view.class));

            }
        });


    }

}