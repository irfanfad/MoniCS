package com.example.mymonics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymonics.model.Misi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailMissionActivity extends AppCompatActivity {

    private static final String TAG = "br";
    private Misi misi;
    private TextView tvNamaMisi, tvJamMulai, tvJamSelesai, tvPoint, tvCountDown,tvCountdownText;
    private Button btnMulai;
    private ImageButton imgBack;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Date dateNow;
    String date;
    String hoursLeft;
    Long serverUptimeSeconds;


    SharedPreferences sharedPreferences2;
    SharedPreferences.Editor editor2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mission);

        tvNamaMisi = findViewById(R.id.tv_nama_misi);
        tvJamMulai = findViewById(R.id.tv_jam_mulai);
        tvJamSelesai = findViewById(R.id.tv_jam_selesai);
        tvCountDown = findViewById(R.id.tv_countdown);
        tvPoint = findViewById(R.id.tv_point);
        tvCountdownText = findViewById(R.id.tv_countdowntext);
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

        sharedPreferences2 = getSharedPreferences("IDMISI", 0);
        editor2 = sharedPreferences2.edit();


        dateNow = new Date();
        date = sharedPreferences.getString("DATENOW", "");
        if (date==null){
            onDestroy();
        }

        tvCountDown.setVisibility(View.VISIBLE);
        tvCountdownText.setVisibility(View.VISIBLE);
        btnMulai.setVisibility(View.INVISIBLE);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailMissionActivity.this, MissionActivity.class);
                startActivity(intent);
            }
        });
        if (date.isEmpty()) {
            tvCountDown.setVisibility(View.INVISIBLE);
            tvCountdownText.setVisibility(View.INVISIBLE);
            btnMulai.setVisibility(View.VISIBLE);

            btnMulai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvCountDown.setVisibility(View.VISIBLE);
                    tvCountdownText.setVisibility(View.VISIBLE);
                    countDownTimer();

                    Intent intent = new Intent(DetailMissionActivity.this, MissionActivity.class);
                    if (date.isEmpty()) {
                        editor2.putString("MISIID",misi.getIdMisi());
                        editor2.commit();
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

    public void countDownTimer(){
        final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date jamMulai = null;
        Date jamSelesai = null;
        try {
            jamMulai = formatter.parse(misi.getJamMulai());
            jamSelesai = formatter.parse(misi.getJamSelesai());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long date = jamSelesai.getTime()- jamMulai.getTime();

        BroadcastService.time = date;
//        CountDownTimer cdt = new CountDownTimer(date, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                tvCountDown.setVisibility(View.VISIBLE);
//                Long serverUptimeSeconds =
//                        (millisUntilFinished) / 1000;
//                hoursLeft = String.format("%d", (serverUptimeSeconds % 86400) / 3600);
//                String minutesLeft = String.format("%d", ((serverUptimeSeconds % 86400) % 3600) / 60);
//                String secondsLeft = String.format("%d", ((serverUptimeSeconds % 86400) % 3600) % 60);
//                tvCountDown.setText(hoursLeft+":"+minutesLeft+":"+secondsLeft);
//                Log.d("tes", hoursLeft+" "+minutesLeft+" "+secondsLeft);
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        }.start();
        startService(new Intent(this, BroadcastService.class));

    }

    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateGUI(intent); // or whatever method used to update your GUI fields
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(br, new IntentFilter(BroadcastService.COUNTDOWN_BR));
        Log.i(TAG, "Registered broacast receiver");
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(br);
        Log.i(TAG, "Unregistered broacast receiver");
    }

    @Override
    public void onStop() {
        try {
            unregisterReceiver(br);
        } catch (Exception e) {
            // Receiver was probably already stopped in onPause()
        }
        super.onStop();
    }
    @Override
    public void onDestroy() {
        stopService(new Intent(this, BroadcastService.class));
        Log.i(TAG, "Stopped service");
        super.onDestroy();
    }

    private void updateGUI(Intent intent) {
        if (intent.getExtras() != null) {
            long millisUntilFinished = intent.getLongExtra("countdown", 0);
            serverUptimeSeconds =
                        (millisUntilFinished) / 1000;
                hoursLeft = String.format("%d", (serverUptimeSeconds % 86400) / 3600);
                String minutesLeft = String.format("%d", ((serverUptimeSeconds % 86400) % 3600) / 60);
                String secondsLeft = String.format("%d", ((serverUptimeSeconds % 86400) % 3600) % 60);
                tvCountDown.setText(hoursLeft+":"+minutesLeft+":"+secondsLeft);
            Log.i(TAG, "Countdown seconds remaining: " +  millisUntilFinished / 1000);
        }
    }

    public String getDateNow(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }
}
