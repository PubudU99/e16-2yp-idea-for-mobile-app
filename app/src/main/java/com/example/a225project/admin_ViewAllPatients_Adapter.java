package com.example.a225project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class admin_ViewAllPatients_Adapter extends RecyclerView.Adapter<admin_ViewAllPatients_Adapter.MyViewHolder> {

    Context context;
    ArrayList<admin_ViewAllPatients_Model> adminViewAllPatientsModels;

    public admin_ViewAllPatients_Adapter(Context context, ArrayList<admin_ViewAllPatients_Model> adminViewAllPatientsModels){
        this.context=context;
        this.adminViewAllPatientsModels=adminViewAllPatientsModels;
    }

    @NonNull
    @Override
    public admin_ViewAllPatients_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.admin_parient_detaills_recyclerview_row,parent,false);


        return new admin_ViewAllPatients_Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull admin_ViewAllPatients_Adapter.MyViewHolder holder, int position) {
        holder.txtName.setText(adminViewAllPatientsModels.get(position).getPatientName());
        holder.txtPatiendID.setText(adminViewAllPatientsModels.get(position).getPatientID());
    }

    @Override
    public int getItemCount() {
        return adminViewAllPatientsModels.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtPatiendID;
        TextView txtName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtPatiendID=itemView.findViewById(R.id.txtPatiendID);
            txtName=itemView.findViewById(R.id.txtName);
        }
    }

}
