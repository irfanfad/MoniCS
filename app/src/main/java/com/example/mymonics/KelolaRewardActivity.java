package com.example.mymonics;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymonics.adapter.KelolaRewardAdapter;
import com.example.mymonics.api.APIClient;
import com.example.mymonics.api.APIInteface;
import com.example.mymonics.model.Reward;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KelolaRewardActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Reward> list;
    KelolaRewardAdapter kelolaRewardAdapter;
    private ImageButton imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_reward);

        list = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.rv_kelola_reward);
        recyclerView.setHasFixedSize(true);
        imgBack = findViewById(R.id.img_back);

        showRecyclerCardView();
        getData();
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KelolaRewardActivity.this, ManagerActivity.class);
                startActivity(intent);
            }
        });
    }
    private void getData() {
        APIInteface apiInteface = APIClient.getApiClient().create(APIInteface.class);

        Call<List<Reward>> call = apiInteface.getReward();
        call.enqueue(new Callback<List<Reward>>() {
            @Override
            public void onResponse(Call<List<Reward>> call, Response<List<Reward>> response) {
                Log.d("masuk", String.valueOf(response.body()));
                list.addAll(response.body());
                recyclerView.setAdapter(kelolaRewardAdapter);
            }

            @Override
            public void onFailure(Call<List<Reward>> call, Throwable t) {

            }
        });
    }

    private void showRecyclerCardView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        kelolaRewardAdapter = new KelolaRewardAdapter(list,this);
        recyclerView.setAdapter(kelolaRewardAdapter);
    }
    public void tambah(View view) {
        Intent intent = new Intent(KelolaRewardActivity.this, AddRewardActivity.class);
        startActivity(intent);
    }
}