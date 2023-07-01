package com.example.a225project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class admin_viewAllNurses_Adapter  extends RecyclerView.Adapter<admin_viewAllNurses_Adapter.MyViewHolder> {
    Context context;
    ArrayList<admin_viewAallNurse_Model> adminViewAallNurseModels;

    public admin_viewAllNurses_Adapter(Context context, ArrayList<admin_viewAallNurse_Model> adminViewAallNurseModels){
        this.context=context;
        this.adminViewAallNurseModels=adminViewAallNurseModels;
    }

    @NonNull
    @Override
    public admin_viewAllNurses_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.admin_view_all_nurses,parent,false);


        return new admin_viewAllNurses_Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull admin_viewAllNurses_Adapter.MyViewHolder holder, int position) {
        holder.txtName.setText(adminViewAallNurseModels.get(position).getPatientName());
        holder.txtPatiendID.setText(adminViewAallNurseModels.get(position).getPatientID());
    }

    @Override
    public int getItemCount() {
        return adminViewAallNurseModels.size();
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
