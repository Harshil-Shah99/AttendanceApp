package com.example.android.attendanceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.attendanceapp.api.AuthAPI;
import com.example.android.attendanceapp.models.Info;
import com.example.android.attendanceapp.models.LoginModel;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AdminLoginActivity extends AppCompatActivity  {

    EditText edtUsername;
    EditText edtPassword;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        edtUsername =  findViewById(R.id.edtUsername);
        edtPassword =  findViewById(R.id.edtPassword);
        btnLogin =  findViewById(R.id.btnLogin);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                organisationLogin();
            }
        });
    }


    void organisationLogin(){

        Retrofit retrofit = new Retrofit.Builder().baseUrl(getResources().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        AuthAPI authAPI = retrofit.create(AuthAPI.class);
        Call<LoginModel> loginCall = authAPI.login(edtUsername.getText().toString(), edtPassword.getText().toString());

        loginCall.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                String token = null;
                String name;
                try {
                    token = response.body().getAccessToken();
                    Info additionalInfo = response.body().getInfo();

                    Gson gson = new Gson();
                    String infoString = gson.toJson(additionalInfo);
                    name = additionalInfo.getName().trim();

                    Intent intent = new Intent(AdminLoginActivity.this, AdminHome2Activity.class);
                    intent.putExtra("org",name);
                    startActivity(intent);

                } catch (NullPointerException e){
                    e.printStackTrace();

                    Toast.makeText(AdminLoginActivity.this, "Invalid Org ID / Password", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Toast.makeText(AdminLoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();


            }
        });
    }
}