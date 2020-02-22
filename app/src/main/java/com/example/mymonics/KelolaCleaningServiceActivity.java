package com.example.mymonics;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymonics.adapter.CleaningServiceAdapter;
import com.example.mymonics.api.APIClient;
import com.example.mymonics.api.APIInteface;
import com.example.mymonics.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KelolaCleaningServiceActivity extends AppCompatActivity {
    private RecyclerView rvCleaningService;
    private List<User> list;
    CleaningServiceAdapter cleaningServiceAdapter;
    private ImageButton imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_cleaning_service);

        list = new ArrayList<>();
        rvCleaningService = (RecyclerView) findViewById(R.id.rv_kelola_cs);
        rvCleaningService.setHasFixedSize(true);
        imgBack = findViewById(R.id.img_back);

        showRecyclerCardView();
        getData();
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KelolaCleaningServiceActivity.this, ManagerActivity.class);
                startActivity(intent);
            }
        });
    }
    private void getData() {
        APIInteface apiInteface = APIClient.getApiClient().create(APIInteface.class);

        Call<List<User>> call = apiInteface.getUser();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.d("masuk", String.valueOf(response.body()));
                list.addAll(response.body());
                rvCleaningService.setAdapter(cleaningServiceAdapter);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

    private void showRecyclerCardView() {
        rvCleaningService.setLayoutManager(new LinearLayoutManager(this));
        cleaningServiceAdapter = new CleaningServiceAdapter(list,this);
        rvCleaningService.setAdapter(cleaningServiceAdapter);
    }

    public void tambah(View view) {
        Intent intent = new Intent(KelolaCleaningServiceActivity.this, AddCleaningServiceActivity.class);
        startActivity(intent);
    }
}
