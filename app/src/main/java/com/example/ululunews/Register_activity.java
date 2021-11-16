package com.example.ululunews;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class Register_activity extends AppCompatActivity {

    EditText et_username, et_email, et_password, et_confirm;
    Button btn_register;
    String name, email, password, confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_username = findViewById(R.id.tf_name);
        et_email = findViewById(R.id.tf_Reqemail);
        et_password = findViewById(R.id.tf_Reqpassword);
        et_confirm = findViewById(R.id.tf_confirmpassword);
        btn_register = findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = et_username.getText().toString();
                email = et_email.getText().toString();
                password = et_password.getText().toString();
                confirm = et_confirm.getText().toString();

                AndroidNetworking.post("https://ululu-news.herokuapp.com/api/register")
                        .addHeaders("Accept", "application/json")
                        .addBodyParameter("name", name)
                        .addBodyParameter("email", email)
                        .addBodyParameter("password", password)
                        .addBodyParameter("password_confirmation", confirm)
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                   String res = response.getString("reg");
                                    if ("true".equals(res)){
                                        Intent intent = new Intent(Register_activity.this, LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(Register_activity.this , "Register Sukses", Toast.LENGTH_SHORT).show();
                                    }
                                }catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }



                            @Override
                            public void onError(ANError anError) {

                            }
                        });
            }
        });

    }



}
