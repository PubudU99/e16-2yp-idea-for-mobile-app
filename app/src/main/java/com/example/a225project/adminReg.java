package com.example.a225project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class adminReg extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseDatabase mFirebaseDatabase;

    // variables edittext

    EditText  name, address, phoneNumber, NIC, birthDate, email, adminID, password;
    ImageButton submitBtn;

    ImageView goBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_reg);

        goBackBtn = findViewById(R.id.goBackbtn);

        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getApplicationContext(), registration.class);
                startActivity(i2);
            }
        });

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        name = findViewById(R.id.regAdminName);
        address = findViewById(R.id.regAdminAddress);
        phoneNumber = findViewById(R.id.regAdminPhoneNum);
        NIC = findViewById(R.id.regAdminNIC);
        birthDate = findViewById(R.id.regAdminBdate);
        email = findViewById(R.id.regAdminEmail);
        adminID = findViewById(R.id.regAdminId);
        password = findViewById(R.id.regAdimPassword);

        submitBtn = findViewById(R.id.btnSubmit);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Assign to variables before register
                String name_s = (name.getText().toString().trim()).toLowerCase();
                String address_s = address.getText().toString().trim();
                String phoneNumber_s = phoneNumber.getText().toString().trim();
                String NIC_s = NIC.getText().toString().trim();
                String birthDate_s = birthDate.getText().toString().trim();
                String email_s = (email.getText().toString().trim()).toLowerCase();
                String adminIdk_s = (adminID.getText().toString().trim()).toLowerCase(); // admin id shoud pass as username
                String pw_s = password.getText().toString().trim();

                // Check whether the all fields are filled.



                if(pw_s.length() >= 6){

                    if(!name_s.isEmpty() && !address_s.isEmpty() && !phoneNumber_s.isEmpty() && !NIC_s.isEmpty() && !birthDate_s.isEmpty() && !adminIdk_s.isEmpty() && !email_s.isEmpty() && !pw_s.isEmpty()){
                        if(checkUserType(adminIdk_s)){
                            registerUser(name_s, address_s, phoneNumber_s,NIC_s, birthDate_s, email_s,adminIdk_s,pw_s);

                        }else {
                            Toast.makeText(adminReg.this, "Please enter username starting with a",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(adminReg.this, "Please fill the all fields",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(adminReg.this, "Password should have more than 6 characters",
                            Toast.LENGTH_SHORT).show();

                }


            }
        });

    }

    private void registerUser(String name, String address, String phoneNumber, String NIC,String birthDate,String email, String adminID ,String  password) {
        // Get a reference to the "caregiver" node in the database
        mDatabase = mFirebaseDatabase.getReference("admin");


        // Check if the username and email already exist in the database
        mDatabase.orderByChild("adminID").equalTo(adminID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean usernameExists = dataSnapshot.exists();

                mDatabase.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean emailExists = dataSnapshot.exists();

                        if (usernameExists || emailExists) {
                            // Username or email already exists, display an error message
                            Toast.makeText(adminReg.this, "Username or email already exists.", Toast.LENGTH_SHORT).show();
                        } else {
                            // Username and email are available, create a new user entry in the database
                            UserAdmin user = new UserAdmin( name,   address,  phoneNumber,   NIC,  birthDate,  email, adminID);
                            mDatabase.child(adminID).setValue(user); // use push if you need to assign an ID based on Firebase
                            registerUserWithEmail(email, password);

                            // Registration successful, proceed to the login activity or perform any necessary actions
                            Toast.makeText(adminReg.this, "Registration successful", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Error occurred while accessing the database
                        Toast.makeText(adminReg.this, "Error accessing database.", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Error occurred while accessing the database
                Toast.makeText(adminReg.this, "Error accessing database.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void registerUserWithEmail(String email, String password) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Registration successful
                            Toast.makeText(adminReg.this, "Registration successful",
                                    Toast.LENGTH_SHORT).show();

                            // Proceed with necessary actions (e.g., navigate to home screen)
                        } else {
                            // Registration failed
                            Toast.makeText(adminReg.this, "Registration failed: " + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    //check the user type is matching.
    private boolean checkUserType(String userid){
        boolean dtype;
        if(Character.toLowerCase(userid.charAt(0)) == 'a'){
            dtype = true;
        }else{
            dtype = false;
        }
        return dtype;

    }

}