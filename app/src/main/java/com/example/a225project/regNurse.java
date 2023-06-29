package com.example.a225project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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

public class regNurse extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseDatabase mFirebaseDatabase;


    // variables edittext

    EditText name, address, phoneNumber, NIC, birthDate, email, adminID, password, assignedWard;
    ImageView submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_nurse);

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        name = findViewById(R.id.editTextText);
        address = findViewById(R.id.editTextText2);
        phoneNumber = findViewById(R.id.editTextText3);
        NIC = findViewById(R.id.editTextText4);
        birthDate = findViewById(R.id.editTextText5);
        email = findViewById(R.id.editTextText6);
        adminID = findViewById(R.id.editTextText7);
        password = findViewById(R.id.editTextText8);
        assignedWard = findViewById(R.id.editTextText9);

        submitBtn = findViewById(R.id.imageView116);

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
                String assignedWard_s = assignedWard.getText().toString().trim();

                // Check whether the all fields are filled.



                if(pw_s.length() >= 6){

                    if(!name_s.isEmpty() && !address_s.isEmpty() && !phoneNumber_s.isEmpty() && !NIC_s.isEmpty() && !birthDate_s.isEmpty() && !adminIdk_s.isEmpty() && !email_s.isEmpty() && !pw_s.isEmpty() && !assignedWard_s.isEmpty()){
                        if(checkUserType(adminIdk_s)){
                            registerUser(name_s, address_s, phoneNumber_s,NIC_s, birthDate_s, email_s,adminIdk_s,pw_s, assignedWard_s);

                        }else {
                            Toast.makeText(regNurse.this, "Please enter username starting with a",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(regNurse.this, "Please fill the all fields",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(regNurse.this, "Password should have more than 6 characters",
                            Toast.LENGTH_SHORT).show();

                }


            }
        });

    }

    private void registerUser(String name, String address, String phoneNumber, String NIC,String birthDate,String email, String adminID ,String  password, String assignedWard) {
        // Get a reference to the "caregiver" node in the database
        mDatabase = mFirebaseDatabase.getReference("nurse");

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
                            Toast.makeText(regNurse.this, "Username or email already exists.", Toast.LENGTH_SHORT).show();
                        } else {
                            // Username and email are available, create a new user entry in the database
                            UserNurse user = new UserNurse( name,   address,  phoneNumber,   NIC,  birthDate,  email, adminID, assignedWard);
                            mDatabase.child(adminID).setValue(user); // use push if you need to assign an ID based on Firebase
                            registerUserWithEmail(email, password);

                            // Registration successful, proceed to the login activity or perform any necessary actions
                            Toast.makeText(regNurse.this, "Registration successful", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Error occurred while accessing the database
                        Toast.makeText(regNurse.this, "Error accessing database.", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Error occurred while accessing the database
                Toast.makeText(regNurse.this, "Error accessing database.", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(regNurse.this, "Registration successful",
                                    Toast.LENGTH_SHORT).show();

                            // Proceed with necessary actions (e.g., navigate to home screen)
                        } else {
                            // Registration failed
                            Toast.makeText(regNurse.this, "Registration failed: " + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    //check the user type is matching.
    private boolean checkUserType(String userid){
        boolean dtype;
        if(Character.toLowerCase(userid.charAt(0)) == 'n'){
            dtype = true;
        }else{
            dtype = false;
        }
        return dtype;

    }

}