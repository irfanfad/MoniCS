package com.example.mymonics.api;

import com.example.mymonics.model.Misi;
import com.example.mymonics.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInteface {

    @FormUrlEncoded
    @POST("login")
    Call<User> getLogin(@Field("username") String username,
                        @Field("password") String password,
                        @Field("jabatan") String jabatan
                        );

    @GET("getMisi")
    Call<List<Misi>> getMisi();
}
