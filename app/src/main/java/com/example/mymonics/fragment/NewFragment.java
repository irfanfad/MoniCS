package com.example.mymonics.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymonics.R;
import com.example.mymonics.adapter.MissionAdapter;
import com.example.mymonics.api.APIClient;
import com.example.mymonics.api.APIInteface;
import com.example.mymonics.model.Laporan;
import com.example.mymonics.model.Misi;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static com.example.mymonics.SessionManager.ID_LOKASI;
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
    String nik,idLokasi;
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferences2;
    SharedPreferences sharedPreferences3,sharedPreferences4;
    SharedPreferences.Editor editor;
    String date, date2, idmisi;
    Button btnLapor;
    Date dateLapor;
    File file;
    String path;

    public NewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_new, container, false);
        rvMission = (RecyclerView) view.findViewById(R.id.recycler_view);
        rvMission.setHasFixedSize(true);
        btnLapor = view.findViewById(R.id.btn_lapor);
        btnLapor.setVisibility(View.INVISIBLE);


        list = new ArrayList<>();


        showRecyclerCardView();

        sharedPreferences = getActivity().getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        nik = sharedPreferences.getString(NIK, "");
        idLokasi = sharedPreferences.getString(ID_LOKASI, "");

        sharedPreferences2 = getActivity().getSharedPreferences("DATE", 0);
        sharedPreferences3 = getActivity().getSharedPreferences("IDMISI", 0);
        sharedPreferences4 = getActivity().getSharedPreferences("time", 0);
        editor = sharedPreferences2.edit();
        dateLapor = new Date();
        date = sharedPreferences2.getString("DATENOW", "");
        date2 = sharedPreferences2.getString("DATELAPOR", "");

        getMission();
        Log.d("nik", nik);


        if (date.isEmpty()) {
            btnLapor.setVisibility(View.INVISIBLE);
        } else {
            btnLapor.setVisibility(View.VISIBLE);
            btnLapor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnLapor.setVisibility(View.INVISIBLE);
                    openCamera();
                }
            });
        }

        return  view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        idmisi = sharedPreferences3.getString("MISIID", "");
        if (resultCode == RESULT_OK && requestCode == 100){
            Bitmap bmp = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            // convert byte array to Bitmap

            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
                    byteArray.length);

            Log.d("tes", bitmap.toString());

            Uri tempUri = getImageUri(getActivity().getApplicationContext(), bitmap);

            file = new File(getRealPathFromURI(tempUri));
            Log.d("final", String.valueOf(file));
            Log.d("misi", idmisi);

        }
        kirimLaporan();
    }

    void alert(){

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Selamat!");
        builder.setMessage("Anda telah menyelesaikan misi ini dengan baik, dan mendapatkan 100 point + bonus 50 point karna berhasil menyelesaikan kurang dari 40 menit");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void kirimLaporan(){
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        builder.addFormDataPart("waktu_mulai", date);
        builder.addFormDataPart("waktu_lapor", date2);
        builder.addFormDataPart("id_misi", idmisi);
        builder.addFormDataPart("nik", nik);
        builder.addFormDataPart("status", "1");
        builder.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
        MultipartBody requestBody = builder.build();
        Log.d("body", file.getName());

        APIInteface apiInteface = APIClient.getApiClient().create(APIInteface.class);
        Call<Laporan> call = apiInteface.lapor(requestBody);
        call.enqueue(new Callback<Laporan>() {
            @Override
            public void onResponse(Call<Laporan> call, Response<Laporan> response) {
                Log.d("lapor", "onResponse: " +response.body());
                sharedPreferences2.edit().remove("DATENOW").apply();
                alert();
            }

            @Override
            public void onFailure(Call<Laporan> call, Throwable t) {
                Log.e("err", "onFailure: "+ t.getLocalizedMessage());
            }
        });
    }

    private void showRecyclerCardView() {
        rvMission.setLayoutManager(new LinearLayoutManager(getContext()));
        missionAdapter = new MissionAdapter(list, getContext());
        rvMission.setAdapter(missionAdapter);
    }

    public void getMission() {
        APIInteface apiInteface = APIClient.getApiClient().create(APIInteface.class);

        Call<List<Misi>> call = apiInteface.getMisi(idLokasi);
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

    public String getDateNow(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

    public void openCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,
                100);
        editor.putString("DATELAPOR", getDateNow(dateLapor));
        editor.commit();
        Log.d("datelapor", getDateNow(dateLapor));


    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, timeStamp, null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (getActivity().getContentResolver() != null) {
            Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }
}
