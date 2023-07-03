package com.example.a225project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView checkupType;
    ImageButton goBackButton;
    private  String text_Checkup_type="Medical Checkup- Routine";
    //text_Checkup_type="Medical Checkup routine";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        checkupType=findViewById(R.id.checkupType);
        goBackButton=findViewById(R.id.goBackbttn1);
        checkupType.setText(text_Checkup_type);


    }
}