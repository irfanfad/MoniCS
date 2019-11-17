package com.example.mymonics.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymonics.R;
import com.example.mymonics.model.Misi;

import java.util.ArrayList;

public class MissionAdapter extends RecyclerView.Adapter<MissionAdapter.MissionViewHolder> implements View.OnClickListener {

    private ArrayList<Misi> listMission;

    public MissionAdapter(ArrayList<Misi> list) {
        this.listMission = list;
    }

    @NonNull
    @Override
    public MissionAdapter.MissionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cv_mission, viewGroup, false);
        return new MissionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MissionAdapter.MissionViewHolder holder, int position) {

        Misi misi = listMission.get(position);

        holder.tvNamaMisi.setText(misi.getNamaMisi());
        holder.tvJamMulai.setText(misi.getJamMulai());
        holder.tvJamSelesai.setText(misi.getJamSelesai());
        holder.tvPoint.setText(misi.getPoint());

        holder.btnMulai.setOnClickListener(this);

    }

    @Override
    public int getItemCount() {
        return listMission.size();
    }

    @Override
    public void onClick(View v) {

    }

    public class MissionViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNamaMisi, tvJamMulai, tvJamSelesai, tvPoint;
        public Button btnMulai;

        public MissionViewHolder(@NonNull View itemView) {
            super(itemView);
            btnMulai = itemView.findViewById(R.id.btn_mulai);
            tvNamaMisi = itemView.findViewById(R.id.tv_nama_misi);
            tvJamMulai = itemView.findViewById(R.id.tv_jam_mulai);
            tvJamSelesai = itemView.findViewById(R.id.tv_jam_selesai);
            tvPoint = itemView.findViewById(R.id.tv_point);
        }
    }
}
