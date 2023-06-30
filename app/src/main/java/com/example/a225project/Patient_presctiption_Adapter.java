package com.example.a225project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Patient_presctiption_Adapter extends RecyclerView.Adapter<Patient_presctiption_Adapter.MyViewHolder> {

    Context context;
    ArrayList<PrescripionModel>  prescriptionmodels;

    public Patient_presctiption_Adapter(Context context, ArrayList<PrescripionModel>  prescriptionmodels){
        this.context=context;
        this.prescriptionmodels=prescriptionmodels;
    }

    @NonNull
    @Override
    public Patient_presctiption_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.prescriprion_recycper_view_row,parent,false);
        return new Patient_presctiption_Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Patient_presctiption_Adapter.MyViewHolder holder, int position) {
        holder.Tablet.setText(prescriptionmodels.get(position).getTabletName());
        holder.Weight.setText(prescriptionmodels.get(position).getTabletWeight());
        holder.perMeal.setText(prescriptionmodels.get(position).getAmount_perMeal());
        holder.noOfDays.setText(prescriptionmodels.get(position).getNo_of_days());
        holder.mealsPerDay.setText(prescriptionmodels.get(position).getNo_of_Meals());
    }

    @Override
    public int getItemCount() {
        return prescriptionmodels.size() ;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Tablet,Weight,perMeal,noOfDays,mealsPerDay;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Tablet=itemView.findViewById(R.id.TabletName);
            Weight=itemView.findViewById(R.id.Weight);
            perMeal=itemView.findViewById(R.id.amountPerMeal);
            noOfDays=itemView.findViewById(R.id.noOfDays);
            mealsPerDay=itemView.findViewById(R.id.mealsPerDay);

        }
    }
}
