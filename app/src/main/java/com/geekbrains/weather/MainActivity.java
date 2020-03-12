package com.geekbrains.weather;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {
    private TextView city_name, info, temperature;
    private Button setting_btn;
    private boolean check_info;
    private int CODE_KEY = 7;
    private ArrayList<String> searchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //        getParcelFromFirstActivity();

        initView();
        Toolbar toolbar = initToolbar();
        initDrawer(toolbar);
        switchActivitySetting();

        seveOnSettings();
    }

    private void seveOnSettings() {
        SharedPreferences prefs = getSharedPreferences("test", Context.MODE_PRIVATE);
        boolean switchState = prefs.getBoolean("switchState", true);
        if (!switchState) {
            info.setText("");
        } else {
            info.setText(R.string.cloudy);
        }
    }

    private void initView() {
        city_name = findViewById(R.id.city_name);
        info = findViewById(R.id.info);
        temperature = findViewById(R.id.temperature);
        setting_btn = findViewById(R.id.setting_btn);
        searchList = new ArrayList<>();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
        if (id == R.id.nav_home) {
        } else if (id == R.id.nav_home) {
            onBackPressed();
        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(MainActivity.this, Setting.class);
            startActivityForResult(intent, CODE_KEY);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Здесь определяем меню приложения (активити)
        getMenuInflater().inflate(R.menu.main, menu);
        final MenuItem search = menu.findItem(R.id.action_search); // поиск пункта меню поиска
        final SearchView searchText = (SearchView) search.getActionView(); // строка поиска
        searchText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // реагирует на конец ввода поиска
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchList.add(searchText.toString());
                Snackbar.make(searchText, query, Snackbar.LENGTH_LONG).show();
                return true;
            }
            // реагирует на нажатие каждой клавиши
            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

        return true;
    }

    private void switchActivitySetting() {
        setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Setting.class);
                startActivityForResult(intent, CODE_KEY);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CODE_KEY && resultCode == Activity.RESULT_OK) {
            boolean dataCheck = Objects.requireNonNull((data)).getBooleanExtra(Setting.KEY_TO_DATA, true);
            String city = data.getStringExtra(Setting.KEY_TO_DATA2);
            if (!dataCheck) {
                info.setText("");
            } else {
                info.setText(R.string.cloudy);
            }
            city_name.setText(city);
        }
    }

//    private void getParcelFromFirstActivity() {
//        Bundle arguments = getIntent().getExtras();
//
//        if(arguments!=null){
//            check_info = (boolean) arguments.get(Setting.KEY_TO_DATA);
//        }
//        Parcel parcel = (Parcel)getIntent().getSerializableExtra(Setting.KEY_TO_DATA);
//        if (parcel.check_info != null) {
//            check_info = true;
//        }
//
//    }
}
