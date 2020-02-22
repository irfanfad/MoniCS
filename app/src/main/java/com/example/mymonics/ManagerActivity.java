package com.example.mymonics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import static com.example.mymonics.SessionManager.NAMA;
import static com.example.mymonics.SessionManager.PREF_NAME;
import static com.example.mymonics.SessionManager.PRIVATE_MODE;

public class ManagerActivity extends AppCompatActivity implements View.OnClickListener {

    SessionManager sessionManager;

    private CardView cvLaporan, cvCleaningService, cvMisi, cvReward, cvLogout;
    private TextView tvNama;
    String nama;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        tvNama = findViewById(R.id.tv_nama);
        cvLaporan = findViewById(R.id.cv_laporan);
        cvCleaningService = findViewById(R.id.cv_cleaningservice);
        cvMisi = findViewById(R.id.cv_mission);
        cvReward = findViewById(R.id.cv_reward);
        cvLogout = findViewById(R.id.cv_logout);

        cvLaporan.setOnClickListener(this);
        cvCleaningService.setOnClickListener(this);
        cvMisi.setOnClickListener(this);
        cvReward.setOnClickListener(this);
        cvLogout.setOnClickListener(this);


        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        sharedPreferences = getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        nama = sharedPreferences.getString(NAMA, "");

        tvNama.setText(nama);
    }

    @Override
    public void onClick(View v) {
        if(v==cvLaporan){
            Intent intent = new Intent(ManagerActivity.this, LaporanActivity.class);
            startActivity(intent);
        }
        if(v==cvCleaningService){
            Intent intent = new Intent(ManagerActivity.this, KelolaCleaningServiceActivity.class);
            startActivity(intent);
        }
        if(v==cvMisi){
            Intent intent = new Intent(ManagerActivity.this, KelolaMisiActivity.class);
            startActivity(intent);
        }
        if(v==cvReward){
            Intent intent = new Intent(ManagerActivity.this, KelolaRewardActivity.class);
            startActivity(intent);
        }
        if (v==cvLogout){
            sessionManager.logout();
        }
    }
}
