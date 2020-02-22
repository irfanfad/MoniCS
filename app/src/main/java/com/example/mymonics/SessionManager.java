package com.example.mymonics;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    public static final int PRIVATE_MODE = 0;

    public static final String PREF_NAME = "LOGIN";
    public static final String LOGIN = "IS_LOGIN";
    public static final String JABATAN = "JABATAN";
    public static final String NIK = "NIK";
    public static final String NAMA = "NAMA";
    public static final String POINT = "POINT";
    public static final String ID_LOKASI = "ID_LOKASI";

    public SessionManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String jabatan, String nik, String nama, String point, String id_lokasi){
        editor.putBoolean(LOGIN, true);
        editor.putString(JABATAN, jabatan);
        editor.putString(NIK, nik);
        editor.putString(NAMA, nama);
        editor.putString(POINT, point);
        editor.putString(ID_LOKASI, id_lokasi);
        editor.commit();
    }

    public boolean isLoggin(){
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin(){
        if (!this.isLoggin()){
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
        }
    }

    public HashMap<String, String> getUserDetail(){
        HashMap<String, String> user = new HashMap<>();
        user.put(JABATAN, sharedPreferences.getString(JABATAN, null));

        return user;
    }

    public void logout(){
        editor.clear();
        editor.commit();
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
