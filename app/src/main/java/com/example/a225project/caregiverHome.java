package com.example.a225project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class caregiverHome extends AppCompatActivity {

    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregiver_home);

        Intent intent=getIntent();
        String Username =intent.getStringExtra("username");

        name= findViewById(R.id.textView137);
        name.setText(Username);

    }
}