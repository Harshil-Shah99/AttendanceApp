package com.example.android.attendanceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.attendanceapp.api.AuthAPI;
import com.example.android.attendanceapp.models.Organisation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MemberLoginActivity extends AppCompatActivity {

    Button button13;
    List<Organisation> orgList;
    EditText orgId;
    EditText username;
    int i;
    ArrayList<String> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_login);


        orgId = findViewById(R.id.editText);
        username = findViewById(R.id.editText8);
        i = 0;
        items = new ArrayList<>();
        button13=findViewById(R.id.button13);


        Retrofit retrofit = new Retrofit.Builder().baseUrl(getResources().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        AuthAPI authAPI = retrofit.create(AuthAPI.class);
        Call<List<Organisation>> fetchOrganization = authAPI.fetchOrganisations();


        fetchOrganization.enqueue(new Callback<List<Organisation>>() {
            @Override
            public void onResponse(Call<List<Organisation>> call, Response<List<Organisation>> response) {

                try {
                    orgList = response.body();
                    /*while(i<orgList.size()) {
                        items.add(orgList.get(i).getName().trim());
                        i=i+1;
                    }*/
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Organisation>> call, Throwable t) {
                Toast.makeText(MemberLoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });


        button13.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String[] creds = new String[2];
                creds[0] = orgId.getText().toString().trim();
                creds[1] = username.getText().toString().trim();

                Intent intent = new Intent(MemberLoginActivity.this,
                        MemberRoomActivity.class);
                intent.putExtra("creds",creds);
                startActivity(intent);
            }
        });






    }
}
