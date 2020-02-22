package com.example.mymonics;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymonics.api.APIClient;
import com.example.mymonics.api.APIInteface;
import com.example.mymonics.model.Misi;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMisiActivity extends AppCompatActivity {
    EditText etNama,etJamMulai,etJamSelesai,etPoint;
    Spinner spLokasi;
    Integer idLokasi;
    Button btnSave;
    private TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_misi);

        etNama = findViewById(R.id.et_nama);
        etJamMulai = findViewById(R.id.et_jam_mulai);
        etJamSelesai = findViewById(R.id.et_jam_selesai);
        etPoint = findViewById(R.id.et_poin);
        spLokasi = findViewById(R.id.sp_lokasi);
        btnSave = findViewById(R.id.btn_save);

        etJamMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialogMulai();
            }
        });
        etJamSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialogSelesai();
            }
        });

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
                Call<Misi> call = apiInteface.addMisi(etNama.getText().toString(),etJamMulai.getText().toString(),etJamSelesai.getText().toString(),idLokasi.toString(),etPoint.getText().toString());
                call.enqueue(new Callback<Misi>() {
                    @Override
                    public void onResponse(Call<Misi> call, Response<Misi> response) {
                        Toast.makeText(AddMisiActivity.this, "Data Berhasil Di Tambahkan", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddMisiActivity.this, KelolaMisiActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Misi> call, Throwable t) {

                    }
                });
            }
        });
    }
    private void showTimeDialogMulai() {

        /**
         * Calendar untuk mendapatkan waktu saat ini
         */
        Calendar calendar = Calendar.getInstance();

        /**
         * Initialize TimePicker Dialog
         */
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                /**
                 * Method ini dipanggil saat kita selesai memilih waktu di DatePicker
                 */
                etJamMulai.setText(String.format("%02d:%02d", hourOfDay, minute));
//                etJamMulai.setText(hourOfDay+":"+minute);
            }
        },
                /**
                 * Tampilkan jam saat ini ketika TimePicker pertama kali dibuka
                 */
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),

                /**
                 * Cek apakah format waktu menggunakan 24-hour format
                 */
                DateFormat.is24HourFormat(this));

        timePickerDialog.show();
    }
    private void showTimeDialogSelesai() {

        /**
         * Calendar untuk mendapatkan waktu saat ini
         */
        Calendar calendar = Calendar.getInstance();

        /**
         * Initialize TimePicker Dialog
         */
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                /**
                 * Method ini dipanggil saat kita selesai memilih waktu di DatePicker
                 */
                etJamMulai.setText(String.format("%02d:%02d", hourOfDay, minute));
            }
        },
                /**
                 * Tampilkan jam saat ini ketika TimePicker pertama kali dibuka
                 */
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),

                /**
                 * Cek apakah format waktu menggunakan 24-hour format
                 */
                DateFormat.is24HourFormat(this));

        timePickerDialog.show();
    }
}