package com.example.ululunews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {


    EditText et_email, et_password;
    Button btn_register, btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_email = findViewById(R.id.tf_email);
        et_password = findViewById(R.id.tf_password);
        btn_login= findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_registerNow);



        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register_activity.class);
                startActivity(intent);
            }
        });
    }

}

