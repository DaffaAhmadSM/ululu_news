package com.example.ululunews;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView sport_cv = findViewById(R.id.sport_cv);
        CardView health_cv = findViewById(R.id.health_cv);
        CardView technology_cv = findViewById(R.id.technology_cv);
        CardView business_cv = findViewById(R.id.business_cv);
        Button profile = findViewById(R.id.profil);

        sport_cv.setOnClickListener(this);
        health_cv.setOnClickListener(this);
        technology_cv.setOnClickListener(this);
        business_cv.setOnClickListener(this);
        profile.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sport_cv:
                Intent intent = new Intent(getApplicationContext(), SportActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Sport berhasil diklik", Toast.LENGTH_SHORT).show();
                break;
            case R.id.health_cv:
                Intent intent2 = new Intent(getApplicationContext(), HealthActivity.class);
                startActivity(intent2);
                Toast.makeText(this, "health berhasil diklik", Toast.LENGTH_SHORT).show();
                break;
            case R.id.technology_cv:
                Intent intent3 = new Intent(getApplicationContext(), TechnologyActivity.class);
                startActivity(intent3);
                Toast.makeText(this, "tech berhasil diklik", Toast.LENGTH_SHORT).show();
                break;
            case R.id.business_cv:
                Intent intent4 = new Intent(getApplicationContext(), BussinessActivity.class);
                startActivity(intent4);
                Toast.makeText(this, "bussiness berhasil diklik", Toast.LENGTH_SHORT).show();
                break;
            case R.id.profil:
                Intent intent5 = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent5);
                Toast.makeText(this, "Profile berhasil diklik", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}