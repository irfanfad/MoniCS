package com.example.mymonics;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymonics.adapter.KelolaMisiAdapter;
import com.example.mymonics.api.APIClient;
import com.example.mymonics.api.APIInteface;
import com.example.mymonics.model.Misi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KelolaMisiActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Misi> list;
    KelolaMisiAdapter kelolaMisiAdapter;
    private ImageButton imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_misi);

        list = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.rv_kelola_misi);
        recyclerView.setHasFixedSize(true);
        imgBack = findViewById(R.id.img_back);

        showRecyclerCardView();
        getData();
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KelolaMisiActivity.this, ManagerActivity.class);
                startActivity(intent);
            }
        });
    }
    private void getData() {
        APIInteface apiInteface = APIClient.getApiClient().create(APIInteface.class);

        Call<List<Misi>> call = apiInteface.getListMisi();
        call.enqueue(new Callback<List<Misi>>() {
            @Override
            public void onResponse(Call<List<Misi>> call, Response<List<Misi>> response) {
                Log.d("masuk", String.valueOf(response.body()));
                list.addAll(response.body());
                recyclerView.setAdapter(kelolaMisiAdapter);
            }

            @Override
            public void onFailure(Call<List<Misi>> call, Throwable t) {

            }
        });
    }

    private void showRecyclerCardView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        kelolaMisiAdapter = new KelolaMisiAdapter(list,this);
        recyclerView.setAdapter(kelolaMisiAdapter);
    }

    public void tambah(View view) {
        Intent intent = new Intent(KelolaMisiActivity.this, AddMisiActivity.class);
        startActivity(intent);
    }
}