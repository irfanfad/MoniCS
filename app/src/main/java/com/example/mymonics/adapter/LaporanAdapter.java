package com.example.mymonics.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymonics.R;
import com.example.mymonics.model.Laporan;

import java.util.List;

public class LaporanAdapter extends RecyclerView.Adapter<LaporanAdapter.LaporanViewHolder> {

    private List<Laporan> listLaporan;
    public Context context;

    public LaporanAdapter(List<Laporan> listLaporan, Context context) {
        this.listLaporan = listLaporan;
        this.context = context;
    }

    @NonNull
    @Override
    public LaporanViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cv_laporan, viewGroup, false);
        return new LaporanAdapter.LaporanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LaporanViewHolder holder, int position) {

        final Laporan laporan = listLaporan.get(position);

        holder.tvNik.setText(laporan.getNik());
        holder.tvNama.setText(laporan.getNama());
        holder.tvNamaMisi.setText(laporan.getNamaMisi());
        holder.tvPoint.setText(""+laporan.getPointMisi());
        Log.d("laporan", laporan.getNama());

        if (laporan.getStatus().equals(1)){
            holder.imgStatus.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icons8_checked_240));
        }else {
            holder.imgStatus.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icons8_cancel_240));
        }

    }

    @Override
    public int getItemCount() {
        return listLaporan.size();
    }

    public class LaporanViewHolder extends RecyclerView.ViewHolder {

        public TextView tvNik,tvNama,tvNamaMisi,tvPoint;
        public ImageView imgStatus;
        public CardView cvLaporan;

        public LaporanViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNik = itemView.findViewById(R.id.tv_nik);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvNamaMisi = itemView.findViewById(R.id.tv_nama_misi);
            tvPoint = itemView.findViewById(R.id.tv_point2);
            imgStatus = itemView.findViewById(R.id.img_status);
            cvLaporan = itemView.findViewById(R.id.cv_laporan);



        }
    }
}
