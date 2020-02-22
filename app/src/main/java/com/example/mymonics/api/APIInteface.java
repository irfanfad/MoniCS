package com.example.mymonics.api;

import com.example.mymonics.model.EasterEgg;
import com.example.mymonics.model.Laporan;
import com.example.mymonics.model.Misi;
import com.example.mymonics.model.Reward;
import com.example.mymonics.model.User;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInteface {

    @FormUrlEncoded
    @POST("login")
    Call<User> getLogin(@Field("username") String username,
                        @Field("password") String password,
                        @Field("jabatan") String jabatan
                        );

    @FormUrlEncoded
    @POST("point")
    Call<User> point(@Field("nik") String nik);

    @FormUrlEncoded
    @POST("ambilReward")
    Call<User> ambilReward(@Field("nik") String nik,
                           @Field("point_reward") String point_reward,
                           @Field("id_reward") String id_reward);

    @FormUrlEncoded
    @POST("easterEgg")
    Call<User> easterEgg(@Field("nik") String nik,
                           @Field("bonus") String bonus);

    @POST("/api/lapor")
    Call<Laporan> lapor(@Body RequestBody file);

    @FormUrlEncoded
    @POST("addUser")
    Call<User> addUser(@Field("nik") String nik,
                         @Field("username") String username,
                         @Field("password") String password,
                         @Field("nama") String nama,
                         @Field("alamat") String alamat,
                         @Field("no_telp") String no_telp,
                         @Field("id_lokasi") String id_lokasi);

    @FormUrlEncoded
    @POST("addMisi")
    Call<Misi> addMisi(@Field("nama_misi") String nama_misi,
                       @Field("jam_mulai") String jam_mulai,
                       @Field("jam_selesai") String jam_selesai,
                       @Field("id_lokasi") String id_lokasi,
                       @Field("point_misi") String point_misi);

    @POST("addReward")
    Call<Reward> addReward(@Body RequestBody file);





    @GET("getMisi")
    Call<List<Misi>> getMisi(@Query("id_lokasi") String id_lokasi);

    @GET("getLaporan")
    Call<List<Laporan>> getLaporan();

    @GET("getData")
    Call<List<User>> getData();

    @GET("getUser")
    Call<List<User>> getUser();

    @GET("getListMisi")
    Call<List<Misi>> getListMisi();

    @GET("getReward")
    Call<List<Reward>> getReward();

    @GET("getEasterEgg")
    Call<EasterEgg> getEasterEgg();



//    @Multipart
//    @POST("/api/Accounts/editaccount")
//    Call<User> editUser (@Header("Authorization") String authorization, @Part("file\"; filename=\"pp.png\" ") RequestBody file , @Part("FirstName") RequestBody fname, @Part("Id") RequestBody id);
}
