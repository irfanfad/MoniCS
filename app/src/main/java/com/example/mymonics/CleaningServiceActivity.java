package com.example.mymonics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import static com.example.mymonics.SessionManager.NAMA;
import static com.example.mymonics.SessionManager.POINT;
import static com.example.mymonics.SessionManager.PREF_NAME;
import static com.example.mymonics.SessionManager.PRIVATE_MODE;

public class CleaningServiceActivity extends AppCompatActivity implements View.OnClickListener {
    SessionManager sessionManager;

    private CardView cvMisi,cvLeaderboard,cvReward,cvLogout;
    private TextView tvNama, tvPoint;
    String point,nama;

    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferences2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaning_service);

        tvNama = findViewById(R.id.tv_nama);
        tvPoint = findViewById(R.id.tv_point);

        cvMisi = findViewById(R.id.cv_misi);
        cvLeaderboard = findViewById(R.id.cv_leaderboard);
        cvReward = findViewById(R.id.cv_reward);
        cvLogout = findViewById(R.id.cv_logout);

        cvMisi.setOnClickListener(this);
        cvLeaderboard.setOnClickListener(this);
        cvReward.setOnClickListener(this);
        cvLogout.setOnClickListener(this);


        sessionManager = new SessionManager(this);

        sessionManager.checkLogin();

        sharedPreferences = getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        sharedPreferences2 = getSharedPreferences("DATE", PRIVATE_MODE);
        point = sharedPreferences.getString(POINT, "");
        nama = sharedPreferences.getString(NAMA, "");
        Log.d("point", point);

        tvNama.setText(nama);
        tvPoint.setText(point);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cv_misi:
                Intent intent = new Intent(CleaningServiceActivity.this, MissionActivity.class);
                startActivity(intent);
                break;
            case R.id.cv_leaderboard:
                intent = new Intent(CleaningServiceActivity.this, LeaderboardActivity.class);
                startActivity(intent);
                break;
            case R.id.cv_reward:
                intent = new Intent(CleaningServiceActivity.this, RewardActivity.class);
                startActivity(intent);
                break;
            case R.id.cv_logout:
                sessionManager.logout();
                sharedPreferences2.edit().remove("DATENOW").apply();
                break;
        }

    }
}
