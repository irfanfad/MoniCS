package com.example.mymonics.api;

import com.example.mymonics.model.Laporan;
import com.example.mymonics.model.Misi;
import com.example.mymonics.model.User;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APIInteface {

    @FormUrlEncoded
    @POST("login")
    Call<User> getLogin(@Field("username") String username,
                        @Field("password") String password,
                        @Field("jabatan") String jabatan
                        );

    @GET("getMisi")
    Call<List<Misi>> getMisi(@Query("nik") String nik);

    @Multipart
    @POST("/api/tes")
    Call<Laporan> lapor(
            @Part("imageNumber") RequestBody description,
            @Part MultipartBody.Part imageFile);

//    @Multipart
//    @POST("/api/Accounts/editaccount")
//    Call<User> editUser (@Header("Authorization") String authorization, @Part("file\"; filename=\"pp.png\" ") RequestBody file , @Part("FirstName") RequestBody fname, @Part("Id") RequestBody id);
}
