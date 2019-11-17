package com.example.mymonics.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mymonics.R;
import com.example.mymonics.api.APIClient;
import com.example.mymonics.api.APIInteface;
import com.example.mymonics.model.Misi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewFragment extends Fragment {


    public NewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getMission();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new, container, false);
    }

    public void getMission(){
        APIInteface apiInteface = APIClient.getApiClient().create(APIInteface.class);

        Call<List<Misi>> call = apiInteface.getMisi();
        call.enqueue(new Callback<List<Misi>>() {
            @Override
            public void onResponse(Call<List<Misi>> call, Response<List<Misi>> response) {
                Log.d("masuk", String.valueOf(response.body()));
            }

            @Override
            public void onFailure(Call<List<Misi>> call, Throwable t) {

            }
        });
    }


}
