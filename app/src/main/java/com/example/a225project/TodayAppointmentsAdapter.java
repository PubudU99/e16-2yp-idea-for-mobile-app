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

public class TodayAppointmentsAdapter extends RecyclerView.Adapter<TodayAppointmentsAdapter.MyViewHolder> {
    Context context;
    ArrayList<TodayAppointmentsModel> todayAppointmentmodels;

    public TodayAppointmentsAdapter(Context context, ArrayList<TodayAppointmentsModel> todayAppointmentmodels) {
        this.context=context;
        this.todayAppointmentmodels=todayAppointmentmodels;
    }

    @NonNull
    @Override
    public TodayAppointmentsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This is the where you inflate the layout (Giving a look to our rows)
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.recycler_view_row,parent, false);
        return new TodayAppointmentsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodayAppointmentsAdapter.MyViewHolder holder, int position) {
        // assigning values to the views we created in the recycler_view_row layout file
        // based on the position of the recycler view
        holder.aptTypeTxt.setText(todayAppointmentmodels.get(position).getAppointment_type());
        holder.aptTimeTxt.setText(todayAppointmentmodels.get(position).getAppointment_time());
        holder.docNameTxt.setText(todayAppointmentmodels.get(position).getDoctor_name());
        holder.docTitleTxt.setText(todayAppointmentmodels.get(position).getDoctor_title());
        holder.imageView.setImageResource(todayAppointmentmodels.get(position).getDoctor_profilePic());
    }

    @Override
    public int getItemCount() {
        // the recycl er view just wants to know the number of items want displayed
        return todayAppointmentmodels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // grabbing the views from our recycler_view_row layout file
        // kinda like in the onCreate method

        ImageView imageView;
        TextView aptTypeTxt,aptTimeTxt,docNameTxt,docTitleTxt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            aptTypeTxt=itemView.findViewById(R.id.apoinment_type);
            aptTimeTxt=itemView.findViewById(R.id.appoinmemt_time);
            docNameTxt=itemView.findViewById(R.id.Doctor_name);
            docTitleTxt=itemView.findViewById(R.id.doctor_title);
            imageView=itemView.findViewById(R.id.Doctor_dp);
        }
    }
}
