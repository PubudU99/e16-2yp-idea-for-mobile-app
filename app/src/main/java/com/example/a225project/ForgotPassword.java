package com.example.a225project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class ForgotPassword extends AppCompatActivity {

    EditText emailtxt;
    ImageView btnSubmit;
    private FirebaseAuth mAuth;

    ImageButton goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mAuth = FirebaseAuth.getInstance(); // Initialize FirebaseAuth

        emailtxt = findViewById(R.id.editTextText31);
        btnSubmit = findViewById(R.id.imageView220);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailtxt.getText().toString().trim();
                resetPassword(email);
            }
        });

        goBack=findViewById(R.id.imageButton6);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void resetPassword(String email) {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Password reset email sent successfully
                            Toast.makeText(ForgotPassword.this, "Password reset email sent. Check your Email",
                                    Toast.LENGTH_SHORT).show();
                            emailtxt.setText("");

                        } else {
                            // Failed to send password reset email
                            Toast.makeText(ForgotPassword.this, "Failed to send password reset email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
