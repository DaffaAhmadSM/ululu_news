package com.example.ululunews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileActivity extends AppCompatActivity {
    TextView nama, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        nama = findViewById(R.id.pf_nama);
        email = findViewById(R.id.profileemail);
        Button btn_logout = findViewById(R.id.btnlogout);
        api();
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void api(){
        AndroidNetworking.get("https://ululu-news.herokuapp.com/api/user")
                .addHeaders("Authorization", "Bearer "+LoginActivity.token)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String id = response.getString("id");
                            String name = response.getString("name");
                            String em = response.getString("email");

                            email.setText(em);
                            nama.setText(name);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    public void logout(){
        AndroidNetworking.post("https://ululu-news.herokuapp.com/api/logout")
                .addHeaders("Authorization", "Bearer "+LoginActivity.token)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String msg = response.getString("message");
                            Toast.makeText(ProfileActivity.this, msg, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

}