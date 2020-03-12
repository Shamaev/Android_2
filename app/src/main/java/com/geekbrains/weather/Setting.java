package com.geekbrains.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.google.android.material.navigation.NavigationView;

public class Setting extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private EditText edit_city;
    private Button apply_btn;
    private Switch switch_info;
    private RecyclerView recyclerView;
    final static String KEY_TO_DATA = "keyToData";
    final static String KEY_TO_DATA2 = "keyToData2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initView();
        Toolbar toolbar = initToolbar();
        initDrawer(toolbar);
        clickApplyBtn();
        loadModel();

//        Сохранение положения свича

        SharedPreferences prefs = getSharedPreferences("test", Context.MODE_PRIVATE);
        boolean switchState = prefs.getBoolean("switchState", true);
        switch_info.setChecked(switchState);

//        ----------------------

//        edit_city.setText(SettingModel.getInstance().getCityName());
    }



    private void initDrawer(Toolbar toolbar) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
    }

    private Toolbar initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        return toolbar;
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        onBackPressed();
        if (id == R.id.nav_home) {
            Intent intent = new Intent(Setting.this, MainActivity.class);
            intent.putExtra(KEY_TO_DATA, switch_info.isChecked());
            intent.putExtra(KEY_TO_DATA2, edit_city.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        } else if (id == R.id.nav_settings) {
            onBackPressed();
        } else if (id == R.id.nav_support) {
            String adress = "https://vk.com/mr_5622";
            Uri uri = Uri.parse(adress);
            Intent openVk = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(openVk);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadModel() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        final String[] cities =
                {
                        getResources().getString(R.string.moscow),
                        getResources().getString(R.string.ivanovo)
                };
        recyclerView.setAdapter(new CityAdapter(cities, new OnCityItemClickListener() {
            @Override
            public void OnClick(String data) {
                edit_city.setText(data);
            }
        }));
    }

    private void initView() {
        edit_city = findViewById(R.id.edit_city);
        apply_btn = findViewById(R.id.setting_Ok);
        switch_info = findViewById(R.id.switch_info);
        recyclerView = findViewById(R.id.list_city);
    }

    //        Сохранение положения свича
        protected void onPause() {
        super.onPause();

        SharedPreferences.Editor ed = getSharedPreferences("test", Context.MODE_PRIVATE).edit();
        ed.putBoolean("switchState", switch_info.isChecked());
        ed.commit();
    }
    //        ----------------------

//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        SharedPreferences prefs = getSharedPreferences("test", Context.MODE_PRIVATE);
//        boolean switchState = prefs.getBoolean("switchState", true);
//        switch_info.setChecked(switchState);
//    }
//
//
//    @Override
//    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
//        super.onSaveInstanceState(outState, outPersistentState);
//        outState.putBoolean(KEY_TO_DATA, switch_info.isChecked());
//    }
//
//    @Override
//    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        switch_info.setChecked(savedInstanceState.getBoolean(KEY_TO_DATA));
//    }

    private void clickApplyBtn() {
        apply_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting.this, MainActivity.class);
                intent.putExtra(KEY_TO_DATA, switch_info.isChecked());
                intent.putExtra(KEY_TO_DATA2, edit_city.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            Intent intent = new Intent(Setting.this, MainActivity.class);
            intent.putExtra(KEY_TO_DATA, switch_info.isChecked());
            setResult(RESULT_OK, intent);
            finish();
        }
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Intent intent = new Intent(Setting.this, MainActivity.class);
//        intent.putExtra(KEY_TO_DATA, switch_info.isChecked());
//        setResult(RESULT_OK, intent);
//        finish();
//    }
}
