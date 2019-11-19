package com.example.mymonics.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymonics.R;
import com.example.mymonics.adapter.MissionAdapter;
import com.example.mymonics.api.APIClient;
import com.example.mymonics.api.APIInteface;
import com.example.mymonics.model.Misi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mymonics.SessionManager.NIK;
import static com.example.mymonics.SessionManager.PREF_NAME;
import static com.example.mymonics.SessionManager.PRIVATE_MODE;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewFragment extends Fragment {

    private RecyclerView rvMission;
    private ArrayList<Misi> list;
    MissionAdapter missionAdapter;
    View view;
    String nik;
    SharedPreferences sharedPreferences;

    public NewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_new, container, false);
        rvMission = (RecyclerView) view.findViewById(R.id.recycler_view);
        rvMission.setHasFixedSize(true);

        list = new ArrayList<>();


        showRecyclerCardView();

        sharedPreferences = getActivity().getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        nik = sharedPreferences.getString(NIK, "");
        getMission();
        Log.d("nik", nik);

        return  view;
    }

    private void showRecyclerCardView() {
        rvMission.setLayoutManager(new LinearLayoutManager(getContext()));
        missionAdapter = new MissionAdapter(list, getContext());
        rvMission.setAdapter(missionAdapter);
    }

    public void getMission() {
        APIInteface apiInteface = APIClient.getApiClient().create(APIInteface.class);

        Call<List<Misi>> call = apiInteface.getMisi(nik);
        call.enqueue(new Callback<List<Misi>>() {
            @Override
            public void onResponse(Call<List<Misi>> call, Response<List<Misi>> response) {
                Log.d("masuk", String.valueOf(response.body()));
                list.addAll(response.body());
                rvMission.setAdapter(missionAdapter);
            }

            @Override
            public void onFailure(Call<List<Misi>> call, Throwable t) {

            }
        });
    }


}
