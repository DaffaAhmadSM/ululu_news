package com.example.ululunews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Favoriteactivity extends AppCompatActivity {
   private  BottomNavigationView bottomNavigationView;
   RecyclerView recyclerView;
   TextView textView;
   favoriteAdapter favoriteAdapters;
    List<Model> model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoriteactivity);
        recyclerView = findViewById(R.id.recycleview_favorite);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RealmConfiguration configuration = new RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
        Realm realm = Realm.getInstance(configuration);
        textView = findViewById(R.id.tv_emptycontent);
        RealmHelper realmHelper = new RealmHelper(realm);
        model = new ArrayList<>();


        model = realmHelper.getAllNews();
        bottomNavigationView = (bottomNavigationView) = findViewById(R.id.bottom_navigation_favorite);
        bottomNavigationView.setSelectedItemId(R.id.favorite);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
        show();
    }

    protected void onRestart() {
        super.onRestart();
        favoriteAdapters.notifyDataSetChanged();
        show();
    }
    public void show(){
        favoriteAdapters = new favoriteAdapter(getApplicationContext(), model, new favoriteAdapter.Callback() {
            @Override
            public void onClick(int position) {
                Model models =  model.get(position);
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("judul", models.getJudul());
                intent.putExtra("deskripsi", models.getDeskripsi());
                intent.putExtra("source", models.getSumber());
                intent.putExtra("gambar", models.getGambar());
                intent.putExtra("id", models.getId());
                startActivity(intent);

            }
        });
        recyclerView.setAdapter(favoriteAdapters);
    }
}