package com.example.android.attendanceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AdminNotifyActivity extends AppCompatActivity {

    Button button12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notify);

        button12=findViewById(R.id.button12);

        button12.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminNotifyActivity.this,
                       AdminHome2Activity.class);
                startActivity(intent);
            }
        });
    }
}
