package com.example.mymonics;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.mymonics.api.APIClient;
import com.example.mymonics.api.APIInteface;
import com.example.mymonics.model.Reward;
import com.example.mymonics.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mymonics.DetailLaporanActivity.url;
import static com.example.mymonics.SessionManager.NIK;
import static com.example.mymonics.SessionManager.PREF_NAME;
import static com.example.mymonics.SessionManager.PRIVATE_MODE;

public class DetailRewardActivity extends AppCompatActivity {
    private Reward reward;
    private TextView tvNamaReward, tvPointReward;
    private ImageView imgReward;
    private ImageButton imgBack;
    private Button btnAmbil;
    String nik, pointReward;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_reward);

        tvNamaReward = findViewById(R.id.tv_nama_reward);
        tvPointReward = findViewById(R.id.tv_point_reward);
        imgReward = findViewById(R.id.img_reward);
        imgBack = findViewById(R.id.img_back_det_reward);
        btnAmbil = findViewById(R.id.btn_ambil);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailRewardActivity.this, RewardActivity.class);
                startActivity(intent);
            }
        });

        Bundle extras = getIntent().getExtras();
        reward = extras.getParcelable("detailReward");

        sharedPreferences = getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        nik = sharedPreferences.getString(NIK, "");

        tvNamaReward.setText(reward.getNamaReward());
        tvPointReward.setText(String.valueOf(reward.getPointReward()));

        Glide.with(this).load(url+reward.getGambarReward()).into(imgReward);

        btnAmbil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIInteface apiInteface = APIClient.getApiClient().create(APIInteface.class);
                Call<User> call = apiInteface.ambilReward(nik, String.valueOf(reward.getPointReward()),String.valueOf(reward.getIdReward()));
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        String message = response.body().getMessage();
                        if (message.equalsIgnoreCase("sukses")){
                            alert();
                        }else{
                            Toast.makeText(DetailRewardActivity.this, "Point Anda Kurang", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }
        });
    }
    public void alert(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah Anda Yakin Ingin Mengambil Reward Ini?");
        builder.setPositiveButton("YA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DetailRewardActivity.this, "Reward Berhasil Diambil", Toast.LENGTH_SHORT).show();
                dialog.cancel();
                Intent intent = new Intent(DetailRewardActivity.this, CleaningServiceActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Intent intent = new Intent(DetailRewardActivity.this, CleaningServiceActivity.class);
                startActivity(intent);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}