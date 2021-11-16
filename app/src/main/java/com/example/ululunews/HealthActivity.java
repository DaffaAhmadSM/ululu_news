package com.example.ululunews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HealthActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Model> modelnya;
    private String Health = "health";
    private MainAdapter main;
    private String judul, deskripsi, sumber, gambar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        recyclerView = findViewById(R.id.health_rv);
        recyclerView.setHasFixedSize(true);
        AndroidNetworking.initialize(getApplicationContext());
        data();

    }
    private void data() {
        modelnya = new ArrayList<>();
        AndroidNetworking.get("https://newsapi.org/v2/top-headlines")
                .addQueryParameter("country","id")
                .addQueryParameter("category", Health)
                .addQueryParameter("apiKey","39acf3d942ab41949db543417876bdcf")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response: ", "yes");
                        try {
                            JSONArray resultArray = response.getJSONArray("articles");
                            for (int i = 0; i < resultArray.length(); i++) {
                                JSONObject resultObj = resultArray.getJSONObject(i);
                                judul = resultObj.getString("title");
                                deskripsi = resultObj.getString("description");
                                gambar = resultObj.getString("urlToImage");
                                sumber = resultObj.getString("publishedAt");
                                modelnya.add(new Model(i,judul, deskripsi, sumber, gambar));
                            }

                            main = new MainAdapter(HealthActivity.this, modelnya, new MainAdapter.Callback() {
                                @Override
                                public void call(int v) {
                                    Model Operator = modelnya.get(v);
                                    Intent move = new Intent(getApplicationContext(), DetailActivity.class);
                                    move.putExtra("deskripsi", Operator.getDeskripsi());
                                    move.putExtra("sumber", Operator.getSumber());
                                    move.putExtra("judul", Operator.getJudul());
                                    move.putExtra("gambar", Operator.getGambar());
                                    startActivity(move);
                                }
                            });
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HealthActivity.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(main);

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "maaf eror", Toast.LENGTH_SHORT).show();
                            Log.d("Error: ", e.toString());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getApplicationContext(), "Something error", Toast.LENGTH_SHORT).show();

                    }

                });
    }
}