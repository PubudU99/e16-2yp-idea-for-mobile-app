package com.example.a225project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class doctorReg extends AppCompatActivity {

    EditText text;
    ImageView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_reg);

        text = findViewById(R.id.editTextText);
        view = findViewById(R.id.imageViewd);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text1 = text.getText().toString();
                System.out.println(text1);
            }
        });




    }
}