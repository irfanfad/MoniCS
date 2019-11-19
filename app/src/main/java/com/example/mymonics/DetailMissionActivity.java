package com.example.mymonics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymonics.model.Misi;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailMissionActivity extends AppCompatActivity {

    private Misi misi;
    private TextView tvNamaMisi, tvJamMulai, tvJamSelesai, tvPoint;
    private Button btnMulai;
    private ImageButton imgBack;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Date dateNow;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mission);

        tvNamaMisi = findViewById(R.id.tv_nama_misi);
        tvJamMulai = findViewById(R.id.tv_jam_mulai);
        tvJamSelesai = findViewById(R.id.tv_jam_selesai);
        tvPoint = findViewById(R.id.tv_point);
        btnMulai = findViewById(R.id.btn_mulai);
        imgBack = findViewById(R.id.img_back_det);

        Bundle extras = getIntent().getExtras();
        misi = extras.getParcelable("detail");

        tvNamaMisi.setText(misi.getNamaMisi());
        tvJamMulai.setText(misi.getJamMulai());
        tvJamSelesai.setText(misi.getJamSelesai());
        tvPoint.setText(misi.getPoint());

        sharedPreferences = getSharedPreferences("DATE", 0);
        editor = sharedPreferences.edit();

        dateNow = new Date();
        date = sharedPreferences.getString("DATENOW", "");

        btnMulai.setVisibility(View.INVISIBLE);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (date.isEmpty()) {
            btnMulai.setVisibility(View.VISIBLE);
            btnMulai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(DetailMissionActivity.this, MissionActivity.class);
                    if (date.isEmpty()) {
                        editor.putString("DATENOW", getDateNow(dateNow));
                        editor.commit();
                        Log.d("dateNow", getDateNow(dateNow));
                        startActivity(intent);
                    } else {
                        startActivity(intent);
                    }
                }
            });
        }
    }

    public String getDateNow(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }
}
