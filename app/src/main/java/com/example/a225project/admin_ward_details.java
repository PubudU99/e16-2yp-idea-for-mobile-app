package com.example.a225project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class admin_ward_details extends AppCompatActivity implements admin_wardAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private admin_wardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_ward_details);

        recyclerView = findViewById(R.id.wardRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<WardModel> options =
                new FirebaseRecyclerOptions.Builder<WardModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("ward"), WardModel.class)
                        .build();

        adapter = new admin_wardAdapter(options);
        adapter.setOnItemClickListener(this); // Set the click listener
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onItemClick(String wardId) {
        Intent intent = new Intent(this, admin_ward_inside_details.class);
        intent.putExtra("wardId", wardId);
        startActivity(intent);
    }
}
