package com.example.a225project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class EditProfile extends AppCompatActivity {

    ImageButton goBack;

    private ImageView profileImageView;
    private ImageButton selectImageButton;

    String userType;
    String username;

    String adminID;
    private static final int PICK_IMAGE_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        profileImageView = findViewById(R.id.imageView168);
        selectImageButton = findViewById(R.id.imageButton4);

        ImageButton goBack = findViewById(R.id.editProfileGoBack);

        adminID = getIntent().getStringExtra("username");
        System.out.println(adminID);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                profileImageView.setImageBitmap(bitmap);

                username = adminID;
                System.out.println(username);
                userType = checkUser(username);
                saveImageToFirebase(bitmap, userType, username);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void saveImageToFirebase(Bitmap imageBitmap, String userType, String username) {
        String userId = username; // Replace with your own logic to get the user ID

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageData = baos.toByteArray();

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        // Generate a unique filename for the image
        String timestamp = String.valueOf(System.currentTimeMillis());

        System.out.println(username);
        String filename = userId + ".jpeg";

        StorageReference imageRef = storageRef.child("profile_pictures/" + filename);
        UploadTask uploadTask = imageRef.putBytes(imageData);

        uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String imageUrl = uri.toString();
                            DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
                            databaseRef.child(userType).child(userId).child("image").setValue(imageUrl)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(EditProfile.this, "Image uploaded and saved", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(EditProfile.this, "Failed to save image", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                    });
                } else {
                    Toast.makeText(EditProfile.this, "Failed to upload image", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // catch the first charactor and find the use type.
    private String checkUser(String username){
        char first_char0 = username.charAt(0);
        char first_char = Character.toLowerCase(first_char0);

        if(first_char == 'a'){
            return "admin";

        } else if (first_char == 'd') {
            return "doctor";

        } else if (first_char == 'n') {
            return "nurse";

        }
        else if (first_char == 'c') {
            return "caregiver";

        } else if (first_char == 'p') {
            return "patient";

        }else{
            return "invalid";
        }

    }
}