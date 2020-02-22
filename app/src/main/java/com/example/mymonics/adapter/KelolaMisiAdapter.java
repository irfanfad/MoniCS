package com.example.mymonics.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymonics.R;
import com.example.mymonics.model.Misi;

import java.util.List;

public class KelolaMisiAdapter extends RecyclerView.Adapter<KelolaMisiAdapter.ViewHolder> {

    private List<Misi> listMisi;
    private Context context;

    public KelolaMisiAdapter(List<Misi> listMisi, Context context) {
        this.listMisi = listMisi;
        this.context = context;
    }

    @NonNull
    @Override
    public KelolaMisiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_misi, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull KelolaMisiAdapter.ViewHolder holder, int position) {

        Misi misi = listMisi.get(position);
        holder.tvLokasi.setText(misi.getNamaLokasi());
        holder.tvNama.setText(misi.getNamaMisi());

    }

    @Override
    public int getItemCount() {
        return listMisi.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvNama;
        TextView tvLokasi;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.tv_nama);
            tvLokasi = itemView.findViewById(R.id.tv_lokasi);

        }
    }

}