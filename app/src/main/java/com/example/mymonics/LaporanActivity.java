package com.example.mymonics;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymonics.adapter.LaporanAdapter;
import com.example.mymonics.api.APIClient;
import com.example.mymonics.api.APIInteface;
import com.example.mymonics.model.Laporan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaporanActivity extends AppCompatActivity {

    private RecyclerView rvLaporan;
    private List<Laporan> list;
    LaporanAdapter laporanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan);

        list = new ArrayList<>();
        rvLaporan = (RecyclerView) findViewById(R.id.rv_laporan);
        rvLaporan.setHasFixedSize(true);

        showRecyclerCardView();
        getLaporan();
    }

    private void getLaporan() {
        APIInteface apiInteface = APIClient.getApiClient().create(APIInteface.class);

        Call<List<Laporan>> call = apiInteface.getLaporan();
        call.enqueue(new Callback<List<Laporan>>() {
            @Override
            public void onResponse(Call<List<Laporan>> call, Response<List<Laporan>> response) {
                Log.d("masuk", String.valueOf(response.body()));
                list.addAll(response.body());
                rvLaporan.setAdapter(laporanAdapter);
            }

            @Override
            public void onFailure(Call<List<Laporan>> call, Throwable t) {

            }
        });
    }

    private void showRecyclerCardView() {
        rvLaporan.setLayoutManager(new LinearLayoutManager(this));
        laporanAdapter = new LaporanAdapter(list,this);
        rvLaporan.setAdapter(laporanAdapter);
    }
}
