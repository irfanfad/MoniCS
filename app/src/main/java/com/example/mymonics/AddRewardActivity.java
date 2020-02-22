package com.example.mymonics;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymonics.api.APIClient;
import com.example.mymonics.api.APIInteface;
import com.example.mymonics.model.Reward;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRewardActivity extends AppCompatActivity {
    EditText etNama,etPoint;
    ImageView imgReward;
    LinearLayout btnGalery;
    File file;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reward);

        etNama = findViewById(R.id.et_nama);
        etPoint = findViewById(R.id.et_poin);
        imgReward = findViewById(R.id.img_reward);
        btnGalery = findViewById(R.id.btn_galery);

        btnSave = findViewById(R.id.btn_save);

        btnGalery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                final int ACTIVITY_SELECT_IMAGE = 1234;
                startActivityForResult(i, ACTIVITY_SELECT_IMAGE);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               kirimData();
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case 1234:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();


                    Bitmap bitmap = BitmapFactory.decodeFile(filePath);
                    /* Now you have choosen image in Bitmap format in object "yourSelectedImage". You can use it in way you want! */
                    Uri tempUri = getImageUri(this.getApplicationContext(), bitmap);
                    imgReward.setImageBitmap(bitmap);

                    file = new File(getRealPathFromURI(tempUri));
                }
        }

    }
    private void kirimData(){
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        builder.addFormDataPart("nama_reward", etNama.getText().toString());
        builder.addFormDataPart("point_reward", etPoint.getText().toString());
        builder.addFormDataPart("gambar_reward", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
        MultipartBody requestBody = builder.build();
        Log.d("body", file.getName());

        APIInteface apiInteface = APIClient.getApiClient().create(APIInteface.class);
        Call<Reward> call = apiInteface.addReward(requestBody);
        call.enqueue(new Callback<Reward>() {
            @Override
            public void onResponse(Call<Reward> call, Response<Reward> response) {
                Log.d("data", response.body().getMessage());
            }

            @Override
            public void onFailure(Call<Reward> call, Throwable t) {
                Log.e("err", "onFailure: "+ t.getLocalizedMessage());
            }
        });
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
        if (this.getContentResolver() != null) {
            Cursor cursor = this.getContentResolver().query(uri, null, null, null, null);
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
