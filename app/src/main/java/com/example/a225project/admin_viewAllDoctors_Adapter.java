package com.example.a225project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class admin_viewAllDoctors_Adapter extends RecyclerView.Adapter<admin_viewAllDoctors_Adapter.MyViewHolder> {
    Context context;
    ArrayList<admin_viewAllDoctors_Model> admin_viewAllDoctors_models;

    public admin_viewAllDoctors_Adapter(Context context, ArrayList<admin_viewAllDoctors_Model> admin_viewAllDoctors_models){
        this.context=context;
        this.admin_viewAllDoctors_models=admin_viewAllDoctors_models;
    }

    @NonNull
    @Override
    public admin_viewAllDoctors_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.admin_alldoctors_recyclerview_row,parent,false);


        return new admin_viewAllDoctors_Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull admin_viewAllDoctors_Adapter.MyViewHolder holder, int position) {
        holder.txtName.setText(admin_viewAllDoctors_models.get(position).getPatientName());
        holder.txtPatiendID.setText(admin_viewAllDoctors_models.get(position).getPatientID());

    }

    @Override
    public int getItemCount() {
        return admin_viewAllDoctors_models.size();
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
