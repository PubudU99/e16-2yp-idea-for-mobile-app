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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;

    static String username;
    static String password;

    ImageButton loginButton;
    Button btnPasswordReset;
    ImageView forgotPasswordBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.editTextTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextTextPassword);

        forgotPasswordBtn=findViewById(R.id.forgorPassword);
        forgotPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(getApplicationContext(),ForgotPassword.class);
                startActivity(i2);
            }
        });

        // Set OnClickListener for the login button
        loginButton = findViewById(R.id.loginBtn);

        //Set onclick listener for login button.

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = usernameEditText.getText().toString().trim();
                password = passwordEditText.getText().toString().trim();


                //startActivity(new Intent(getApplicationContext(), adminHome.class));

                // check user type.
                String invalidUserReturns = "invalid";
                String userType;

                if(username.length() != 0){
                    userType = checkUser(username);
                }else{
                    userType = invalidUserReturns;
                }


                if(invalidUserReturns.equals(userType)){
                    Toast.makeText(MainActivity.this, "Invalid username1.", Toast.LENGTH_SHORT).show();

                }else{
                    // class for each usertype.
                    Class<?> userActivity = getActivity(userType);

                    getEmailForUsername(username, password, userActivity, userType);
                }

            }
        });

        //btnPasswordReset = findViewById(R.id.pw_reset);

//        btnPasswordReset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //startActivity(new Intent(getApplicationContext(), PasswordResetActivity.class));
//            }
//        });
    }

    private void loginUserWithEmail(String email, String password, Class<?> userActivity, String username) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Login successful
                            Toast.makeText(MainActivity.this, "Login successful",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, userActivity);
                            intent.putExtra("username", username);
                            startActivity(intent);

                            // Proceed with necessary actions (e.g., navigate to home screen)
                        } else {
                            // Login failed
                            Toast.makeText(MainActivity.this, "Login failed: " + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            System.out.println("Login failed: " + task.getException().getMessage());
                        }
                    }
                });
    }

    private void getEmailForUsername(String username, String password, Class<?> userActivity, String usertype) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference(usertype);
        Query query = usersRef.orderByChild("adminID").equalTo(username);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    DataSnapshot userSnapshot = dataSnapshot.getChildren().iterator().next();
                    String email = userSnapshot.child("email").getValue(String.class);
                    if (email != null) {
                        // Email found for the given username

                        Toast.makeText(MainActivity.this, "Email: " + email,
                                Toast.LENGTH_SHORT).show();
                        loginUserWithEmail(email, password, userActivity,username);

                    } else {
                        // Email not found for the given username
                        Toast.makeText(MainActivity.this, "Email not found for username: " + username,
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // No user found with the given username
                    Toast.makeText(MainActivity.this, "No user found with username: " + username,
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Error occurred while accessing the database
                Toast.makeText(MainActivity.this, "Error accessing database: " + databaseError.getMessage(),
                        Toast.LENGTH_SHORT).show();
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

    // return the class based on the usertype.
    private Class<?>  getActivity(String usertype){
        if(usertype.equals("patient")){
            return MainActivity3.class;

        } else if (usertype.equals("nurse")) {
            return nursrHome.class;

        }else if (usertype.equals("doctor")) {
            return Doctor_HomePage.class;


        }else if (usertype.equals("caregiver")) {
            return caregiverHome.class;
        }
        else{
            return adminHome.class; // return admin class.
        }

    }
}