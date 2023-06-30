package com.example.a225project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class doctorTodayPatientsAdapter extends RecyclerView.Adapter<doctorTodayPatientsAdapter.MyViewHolder> {

    Context context;
    ArrayList<doctorTodayPatientsModel> doctorTodayPatientsModels;

    public doctorTodayPatientsAdapter(Context context , ArrayList<doctorTodayPatientsModel> doctorTodayPatientsModels){
        this.context=context;
        this.doctorTodayPatientsModels=doctorTodayPatientsModels;
    }
    @NonNull
    @Override
    public doctorTodayPatientsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.doctor_home_recyclerview_columns,parent,false);
        return new doctorTodayPatientsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull doctorTodayPatientsAdapter.MyViewHolder holder, int position) {
        holder.PatientName.setText(doctorTodayPatientsModels.get(position).getPatient_Name());
        holder.WardID.setText(doctorTodayPatientsModels.get(position).getWard_ID());
        holder.BedID.setText(doctorTodayPatientsModels.get(position).getBed_No());
        holder.Time.setText(doctorTodayPatientsModels.get(position).getTime());
        holder.profilePic.setImageResource(doctorTodayPatientsModels.get(position).getPatient_profilePic());

    }

    @Override
    public int getItemCount() {
        return doctorTodayPatientsModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView profilePic;
        TextView PatientName,WardID,BedID,Time;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            profilePic = itemView.findViewById(R.id.parientProfilePic);
            PatientName=itemView.findViewById(R.id.txtPatientName);
            WardID=itemView.findViewById(R.id.txtWardID);
            BedID=itemView.findViewById(R.id.txtBedNO);
            Time=itemView.findViewById(R.id.txtTime);
        }
    }
}
