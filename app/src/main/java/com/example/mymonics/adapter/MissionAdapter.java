package com.example.mymonics.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymonics.DetailMissionActivity;
import com.example.mymonics.R;
import com.example.mymonics.model.Misi;

import java.util.ArrayList;

public class MissionAdapter extends RecyclerView.Adapter<MissionAdapter.MissionViewHolder> {

    private ArrayList<Misi> listMission;
    public Context context;
    String date;
    private static final int IMAGE_CAPTURE_CODE = 1001;

    Uri imageUri;

    SharedPreferences sharedPreferences;


    public MissionAdapter(ArrayList<Misi> listMission, Context context) {
        this.listMission = listMission;
        this.context = context;
    }


    @NonNull
    @Override
    public MissionAdapter.MissionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cv_mission, viewGroup, false);
        return new MissionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MissionAdapter.MissionViewHolder holder, int position) {

        final Misi misi = listMission.get(position);

        holder.tvNamaMisi.setText(misi.getNamaMisi());
        holder.tvJamMulai.setText(misi.getJamMulai());
        holder.tvJamSelesai.setText(misi.getJamSelesai());
        holder.tvPoint.setText(misi.getPoint());
        sharedPreferences = context.getSharedPreferences("DATE", 0);
        date = sharedPreferences.getString("DATENOW", "");

        holder.cvDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context.getApplicationContext(), DetailMissionActivity.class);
                intent.putExtra("detail", misi);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listMission.size();
    }


    public class MissionViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNamaMisi, tvJamMulai, tvJamSelesai, tvPoint;
        public CardView cvDetail;

        public MissionViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaMisi = itemView.findViewById(R.id.tv_nama_misi);
            tvJamMulai = itemView.findViewById(R.id.tv_jam_mulai);
            tvJamSelesai = itemView.findViewById(R.id.tv_jam_selesai);
            tvPoint = itemView.findViewById(R.id.tv_point);
            cvDetail = itemView.findViewById(R.id.cv_misi);
        }
    }
}
