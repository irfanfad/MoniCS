package com.example.mymonics;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ManagerActivity extends AppCompatActivity {

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
    }
}
