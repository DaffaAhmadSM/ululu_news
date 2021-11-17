package com.example.ululunews;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {


    EditText et_email, et_password;
    Button btn_register, btn_login;
    String email, password;
    static String token;
    LocalStorage localStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        localStorage = new LocalStorage(LoginActivity.this);

        et_email = findViewById(R.id.tf_email);
        et_password = findViewById(R.id.tf_password);
        btn_login= findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_registerNow);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, Register_activity.class);
                startActivity(intent);
            }
        });
    }

    public void login() {
        email = et_email.getText().toString();
        password = et_password.getText().toString();

        AndroidNetworking.post("https://ululu-news.herokuapp.com/api/login")
                .addHeaders("Accept", "application/json")
                .addBodyParameter("email", email)
                .addBodyParameter("password", password)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            token = response.getString("token");
                            String log = response.getString("log");
                            if ("true".equals(log)){
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(LoginActivity.this , "Login Sukses", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(LoginActivity.this , "Error", Toast.LENGTH_SHORT).show();
                            }
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

