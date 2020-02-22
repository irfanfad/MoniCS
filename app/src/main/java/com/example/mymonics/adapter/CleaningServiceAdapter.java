package com.example.mymonics.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymonics.R;
import com.example.mymonics.model.User;

import java.util.List;

public class CleaningServiceAdapter extends RecyclerView.Adapter<CleaningServiceAdapter.ViewHolder> {

    private List<User> listCleaningService;
    private Context context;

    public CleaningServiceAdapter(List<User> listCleaningService, Context context) {
        this.listCleaningService = listCleaningService;
        this.context = context;
    }

    @NonNull
    @Override
    public CleaningServiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_cleaning_service, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CleaningServiceAdapter.ViewHolder holder, int position) {

        User user = listCleaningService.get(position);
        holder.tvNik.setText(String.valueOf(user.getNik()));
        holder.tvNama.setText(user.getNama());
        holder.tvLokasi.setText(user.getNamaLokasi());

    }

    @Override
    public int getItemCount() {
        return listCleaningService.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvNik;
        TextView tvNama;
        TextView tvLokasi;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvLokasi = itemView.findViewById(R.id.tv_lokasi);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvNik = itemView.findViewById(R.id.tv_nik);

        }
    }

}