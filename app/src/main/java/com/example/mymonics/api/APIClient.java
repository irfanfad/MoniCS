package com.example.mymonics.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    public static Retrofit retrofit = null;

    //    private static OkHttpClient buildClient() {
//        return new OkHttpClient
//                .Builder()
//                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//                .build();
//    }
//
    public static Retrofit getApiClient(){

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://monics.victim.id/")
                    .addConverterFactory(retrofit2.converter.scalars.ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}