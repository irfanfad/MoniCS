package com.example.mymonics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymonics.api.APIClient;
import com.example.mymonics.api.APIInteface;
import com.example.mymonics.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCleaningServiceActivity extends AppCompatActivity {
    EditText etNik,etUsername,etPassword,etNama,etAlamat,etNoTelp;
    Spinner spLokasi;
    Integer idLokasi;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cleaning_service);

        etNik = findViewById(R.id.nik);
        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);
        etNama = findViewById(R.id.nama);
        etAlamat = findViewById(R.id.alamat);
        etNoTelp = findViewById(R.id.no_telp);
        spLokasi = findViewById(R.id.splokasi);
        btnSave = findViewById(R.id.btnSave);

        spLokasi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String lokasi = parent.getItemAtPosition(position).toString();
                if (lokasi.equalsIgnoreCase("Halaman dan Basemant")) {
                    idLokasi=1;
                }if (lokasi.equalsIgnoreCase("Lobby utama gedung baru")) {
                    idLokasi=2;
                }if (lokasi.equalsIgnoreCase("Lantai 5")) {
                    idLokasi=3;
                }if (lokasi.equalsIgnoreCase("Lantai 8")) {
                    idLokasi=4;
                }if (lokasi.equalsIgnoreCase("Lantai 11")) {
                    idLokasi=5;
                }if (lokasi.equalsIgnoreCase("Lantai 15 dan 17")) {
                    idLokasi=6;
                }if (lokasi.equalsIgnoreCase("Ekonomi, MI 2 dan IF 3, Robotika")) {
                    idLokasi=7;
                }if (lokasi.equalsIgnoreCase("Front office lobby utama gedung lama")) {
                    idLokasi=8;
                }if (lokasi.equalsIgnoreCase("Pasca sarjana lantai 2 dan 3 kampus 4")) {
                    idLokasi=9;
                }if (lokasi.equalsIgnoreCase("Labkom  lantai 2 dan 3")) {
                    idLokasi=10;
                }if (lokasi.equalsIgnoreCase("Lantai 7 dan lantai 6 kampus 4")) {
                    idLokasi=11;
                }if (lokasi.equalsIgnoreCase("Kampus dago")) {
                    idLokasi=12;
                }if (lokasi.equalsIgnoreCase("Yayasan DU 99")) {
                    idLokasi=13;
                }if (lokasi.equalsIgnoreCase("Radio Hits Unikom")) {
                    idLokasi=14;
                }if (lokasi.equalsIgnoreCase("Halaman dan Basemant (Siang)")) {
                    idLokasi=15;
                }if (lokasi.equalsIgnoreCase("Lantai 5 (Siang)")) {
                    idLokasi=16;
                }if (lokasi.equalsIgnoreCase("Ekonomi, MI 2 dan IF 3, Robotika (Siang)")) {
                    idLokasi=17;
                }if (lokasi.equalsIgnoreCase("Pasca sarjana lantai 2 dan 3 kampus 4 (Siang)")) {
                    idLokasi=18;
                }if (lokasi.equalsIgnoreCase("Kampus dago (Siang)")) {
                    idLokasi=19;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIInteface apiInteface = APIClient.getApiClient().create(APIInteface.class);
                Call<User> call = apiInteface.addUser(etNik.getText().toString(),etUsername.getText().toString(),etPassword.getText().toString(),etNama.getText().toString(),etAlamat.getText().toString(),etNoTelp.getText().toString(),idLokasi.toString());
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast.makeText(AddCleaningServiceActivity.this, "Data Berhasil Di Tambahkan", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddCleaningServiceActivity.this, KelolaCleaningServiceActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }
        });
    }
}
