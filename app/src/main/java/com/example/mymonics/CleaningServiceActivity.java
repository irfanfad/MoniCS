package com.example.mymonics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mymonics.api.APIClient;
import com.example.mymonics.api.APIInteface;
import com.example.mymonics.model.Misi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CleaningServiceActivity extends AppCompatActivity implements View.OnClickListener {
    SessionManager sessionManager;

    private CardView cvMisi,cvLeaderboard,cvReward,cvLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaning_service);

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cv_misi:
                Intent intent = new Intent(CleaningServiceActivity.this, MissionActivity.class);
                startActivity(intent);
                break;
            case R.id.cv_leaderboard:
                //
                break;
            case R.id.cv_reward:
                //
                break;
            case R.id.cv_logout:
                sessionManager.logout();
                break;
        }

    }
}
