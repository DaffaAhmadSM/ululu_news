package com.example.ululunews;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Register_activity extends AppCompatActivity {

    EditText et_username, et_email, et_password;
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_username = findViewById(R.id.tf_name);
        et_email = findViewById(R.id.tf_email);
        et_password = findViewById(R.id.tf_password);
        btn_register = findViewById(R.id.btn_register);

    }

}
