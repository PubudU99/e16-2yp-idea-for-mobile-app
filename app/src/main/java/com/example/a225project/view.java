package com.example.a225project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class view extends AppCompatActivity {

    ImageView goBackBtn;
    ImageView viewPatients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        goBackBtn = findViewById(R.id.imageView95);

        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getApplicationContext(), adminHome.class);
                startActivity(i2);
            }
        });
        viewPatients=findViewById(R.id.imageView77);
        viewPatients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1= new Intent(getApplicationContext(),Admin_viewAllPatients.class);
                startActivity(i1);
            }
        });

    }


}