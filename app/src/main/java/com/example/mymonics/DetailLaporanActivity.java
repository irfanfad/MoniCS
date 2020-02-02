package com.example.mymonics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.mymonics.model.Laporan;

public class DetailLaporanActivity extends AppCompatActivity {
    private Laporan laporan;
    private TextView tvNamaCs,tvNamaMisi,tvJamMulai,tvJamLapor,tvPoint;
    private ImageView imgLaporan;
    private ImageButton imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_laporan);

        tvNamaCs = findViewById(R.id.tv_nama_cs);
        tvNamaMisi = findViewById(R.id.tv_nama_misi);
        tvJamMulai = findViewById(R.id.tv_jam_mulai);
        tvJamLapor = findViewById(R.id.tv_jam_lapor);
        tvPoint = findViewById(R.id.tv_point_lapor);
        imgLaporan = findViewById(R.id.img_laporan);
        imgBack = findViewById(R.id.img_back_det_lap);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailLaporanActivity.this, LaporanActivity.class);
            }
        });

        Bundle extras = getIntent().getExtras();
        laporan = extras.getParcelable("detailLapor");

        tvNamaCs.setText(laporan.getNama());
        tvNamaMisi.setText(laporan.getNamaMisi());
        tvJamMulai.setText(laporan.getWaktuMulai());
        tvJamLapor.setText(laporan.getWaktuMulai());
        //tvPoint.setText(laporan.getPointMisi());

        Glide.with(this).load("http://monics.victim.id/storage/gambar/"+laporan.getGambarLapor()).into(imgLaporan);


    }
}
