package com.example.mymonics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.mymonics.api.APIClient;
import com.example.mymonics.api.APIInteface;
import com.example.mymonics.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mymonics.SessionManager.NAMA;
import static com.example.mymonics.SessionManager.NIK;
import static com.example.mymonics.SessionManager.PREF_NAME;
import static com.example.mymonics.SessionManager.PRIVATE_MODE;

public class CleaningServiceActivity extends AppCompatActivity implements View.OnClickListener {
    SessionManager sessionManager;

    private CardView cvMisi,cvLeaderboard,cvReward,cvLogout;
    private TextView tvNama, tvPoint;
    private ImageView imgEasterEgg;
    String nama,nik;
    Boolean btnClick=false;

    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferences2,sharedPreferences3;
    SharedPreferences.Editor editor3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaning_service);

        tvNama = findViewById(R.id.tv_nama);
        tvPoint = findViewById(R.id.tv_point);
        imgEasterEgg = findViewById(R.id.img_easteregg);

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
        sharedPreferences3 = getSharedPreferences("EASTEREGG", 0);
        editor3 = sharedPreferences3.edit();

        nama = sharedPreferences.getString(NAMA, "");
        nik = sharedPreferences.getString(NIK, "");
        Boolean click = sharedPreferences3.getBoolean("EASTEREGG",false);
        Log.d("klik", String.valueOf(click));
        openEasterEgg();
        if(click==true){
            imgEasterEgg.setVisibility(View.INVISIBLE);
        }

        tvNama.setText(nama);



        Log.d("nik", nik);

        imgEasterEgg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnClick = true;
                editor3.putBoolean("EASTEREGG", btnClick);
                editor3.commit();
                Intent intent = new Intent(CleaningServiceActivity.this,EasterEggActivity.class);
                startActivity(intent);
            }
        });


        APIInteface apiInteface = APIClient.getApiClient().create(APIInteface.class);
        Call<User> call = apiInteface.point(nik);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("tes", String.valueOf(response.body().getPoint()));
                tvPoint.setText(String.valueOf(response.body().getPoint()));
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

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
                sharedPreferences3.edit().remove("EASTEREGG").apply();
                break;
        }

    }
    public void openEasterEgg(){
        if(checktimings("08:00","10:00")){
            imgEasterEgg.setVisibility(View.VISIBLE);
        }else {
            imgEasterEgg.setVisibility(View.INVISIBLE);
            Log.d("easter", "salah");
        }

    }
    private boolean checktimings(String time, String endtime) {

        String pattern = "HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar c = Calendar.getInstance();

        try {
            String currentTime = sdf.format(c.getTime());
            Date current = sdf.parse(currentTime);
            Date date1 = sdf.parse(time);
            Date date2 = sdf.parse(endtime);

            if(current.before(date2) && current.after(date1)) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e){
            e.printStackTrace();
        }
        return false;
    }
}
