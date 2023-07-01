package com.example.a225project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NursePatientRecyclerViewAdapter extends RecyclerView.Adapter<NursePatientRecyclerViewAdapter.PatientViewHolder> {

    private List<Patient> patientList;

    public NursePatientRecyclerViewAdapter(List<Patient> patientList) {
        this.patientList = patientList;
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
            txtPatientName = itemView.findViewById(R.id.txtName);
            txtPatientID = itemView.findViewById(R.id.txtPatiendID);
        }

        public void bind(Patient patient) {
            txtPatientName.setText(patient.getName());
            txtPatientID.setText(patient.getAdminID());
        }
    }
}
