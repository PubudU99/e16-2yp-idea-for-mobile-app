package com.example.a225project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class admin_wardAdapter extends FirebaseRecyclerAdapter<WardModel, admin_wardAdapter.ViewHolder> {

    private OnItemClickListener itemClickListener;

    public admin_wardAdapter(@NonNull FirebaseRecyclerOptions<WardModel> options) {
        super(options);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull WardModel model) {
        holder.txtPatiendID.setText(model.getId());
        holder.txtName.setText(model.getName());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_ward_recycler_view_row, parent, false);
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtPatiendID, txtName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPatiendID = itemView.findViewById(R.id.txtadminCaregiverID);
            txtName = itemView.findViewById(R.id.txtadminCaregiverName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && itemClickListener != null) {
                        String wardId = getItem(position).getId();
                        itemClickListener.onItemClick(wardId);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String wardId);
    }
}
