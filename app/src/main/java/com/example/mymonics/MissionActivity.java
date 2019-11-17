package com.example.mymonics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mymonics.adapter.PagerAdapter;
import com.example.mymonics.api.APIClient;
import com.example.mymonics.api.APIInteface;
import com.example.mymonics.fragment.NewFragment;
import com.example.mymonics.fragment.PastFragment;
import com.example.mymonics.fragment.ProgressFragment;
import com.example.mymonics.model.Misi;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MissionActivity extends AppCompatActivity {

    private ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        final ViewPager viewPager = findViewById(R.id.view_pager);
        final PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());

        pagerAdapter.addFragment(new NewFragment(),"New");
        pagerAdapter.addFragment(new ProgressFragment(),"In Progress");
        pagerAdapter.addFragment(new PastFragment(),"Past");
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
