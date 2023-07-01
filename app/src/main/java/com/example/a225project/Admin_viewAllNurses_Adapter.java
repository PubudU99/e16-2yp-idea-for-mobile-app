package com.example.a225project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Admin_viewAllNurses_Adapter extends FirebaseRecyclerAdapter<MainModel, Admin_viewAllNurses_Adapter.ViewHolder> {

    public Admin_viewAllNurses_Adapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull MainModel model) {
        holder.txtPatiendID.setText(model.getAdminID());
        holder.txtName.setText(model.getName());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_view_all_nurses, parent, false);
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtPatiendID, txtName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPatiendID = itemView.findViewById(R.id.txtPatiendID);
            txtName = itemView.findViewById(R.id.txtName);
        }
    }
}
