package com.example.mymonics;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymonics.api.APIClient;
import com.example.mymonics.api.APIInteface;
import com.example.mymonics.model.EasterEgg;
import com.example.mymonics.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mymonics.SessionManager.NIK;
import static com.example.mymonics.SessionManager.PREF_NAME;
import static com.example.mymonics.SessionManager.PRIVATE_MODE;

public class EasterEggActivity extends AppCompatActivity {
    private TextView tvSoal;
    private Button btnPilihan1,btnPilihan2,btnPilihan3,btnPilihan4;
    String jawaban1,jawaban2,jawaban3,jawaban4,nik;
    Integer bonus;
    Integer bonusBenar, bonusSalah;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easter_egg);

        tvSoal = findViewById(R.id.tv_soal);
        btnPilihan1 = findViewById(R.id.btn_pilihan1);
        btnPilihan2 = findViewById(R.id.btn_pilihan2);
        btnPilihan3 = findViewById(R.id.btn_pilihan3);
        btnPilihan4 = findViewById(R.id.btn_pilihan4);

        sharedPreferences = getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        nik = sharedPreferences.getString(NIK, "");

        APIInteface apiInteface = APIClient.getApiClient().create(APIInteface.class);

        Call<EasterEgg> call = apiInteface.getEasterEgg();
        call.enqueue(new Callback<EasterEgg>() {
            @Override
            public void onResponse(Call<EasterEgg> call, Response<EasterEgg> response) {
                tvSoal.setText(response.body().getSoal());
                jawaban1 = response.body().getJawaban();
                jawaban2 = response.body().getSalah1();
                jawaban3 = response.body().getSalah2();
                jawaban4 = response.body().getSalah3();
                bonusBenar = response.body().getBonusBenar();
                bonusSalah = response.body().getBonusSalah();
                btnPilihan1.setText(jawaban1);
                btnPilihan2.setText(jawaban2);
                btnPilihan3.setText(jawaban3);
                btnPilihan4.setText(jawaban4);
            }

            @Override
            public void onFailure(Call<EasterEgg> call, Throwable t) {

            }
        });

        btnPilihan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekJawaban(jawaban1,jawaban1);
                kirimData(bonusBenar);
            }
        });
        btnPilihan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekJawaban(jawaban2,jawaban1);
                kirimData(bonusSalah);
            }
        });
        btnPilihan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekJawaban(jawaban3,jawaban1);
                kirimData(bonusSalah);
            }
        });
        btnPilihan4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekJawaban(jawaban4,jawaban1);
                kirimData(bonusSalah);
            }
        });
    }

    private void cekJawaban(String jawaban, String jawab){
        if(jawaban.equalsIgnoreCase(jawab)){
            alertBenar();
        }else {
            alertSalah();
        }

    }

    private void kirimData(Integer bonus){
        APIInteface apiInteface = APIClient.getApiClient().create(APIInteface.class);
        Call<User> call = apiInteface.easterEgg(nik, String.valueOf(bonus));
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }
    public void alertBenar(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selamat! ");
        builder.setMessage("Anda Mendapatkan 100 Point karna jawaban anda benar");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Intent intent = new Intent(EasterEggActivity.this, CleaningServiceActivity.class);
                startActivity(intent);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void alertSalah(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Jawaban anda salah");
        builder.setMessage("Jawaban yang benar adalah "+jawaban1+", Tapi jangan berkecil hati anda tetap mendapat 40 Point");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Intent intent = new Intent(EasterEggActivity.this, CleaningServiceActivity.class);
                startActivity(intent);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
