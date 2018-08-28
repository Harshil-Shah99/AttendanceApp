package com.example.android.attendanceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AdminPagerActivity extends AppCompatActivity {

    Button locationcheck;
    Button generatetoken;
    Button checkstats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pager);

        locationcheck=findViewById(R.id.checklocation);
        generatetoken=findViewById(R.id.generatetoken);
        checkstats=findViewById(R.id.checkstats);

        locationcheck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminPagerActivity.this,
                        AdminActivity.class);
                startActivity(intent);
            }
        });

        generatetoken.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminPagerActivity.this,
                        TokenActivity.class);
                startActivity(intent);
            }
        });

    }
}
