package com.example.a225project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


import android.content.Context;
import android.content.Intent;


public class NursePatientRecyclerViewAdapter extends RecyclerView.Adapter<NursePatientRecyclerViewAdapter.PatientViewHolder> {

    private List<Patient> patientList;
    private Context context;

    public NursePatientRecyclerViewAdapter(List<Patient> patientList, Context context) {
        this.patientList = patientList;
        this.context = context;
    }

    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_parient_detaills_recyclerview_row, parent, false);
        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {
        Patient patient = patientList.get(position);
        holder.bind(patient);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, nursePatientDetails.class);
                intent.putExtra("patientId", patient.getAdminID());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
        notifyDataSetChanged();
    }

    public static class PatientViewHolder extends RecyclerView.ViewHolder {
        private TextView txtPatientName;
        private TextView txtPatientID;

        public PatientViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPatientName = itemView.findViewById(R.id.txtadminCaregiverName);
            txtPatientID = itemView.findViewById(R.id.txtadminCaregiverID);
        }

        public void bind(Patient patient) {
            txtPatientName.setText(patient.getName());
            txtPatientID.setText(patient.getAdminID());
        }
    }
}
