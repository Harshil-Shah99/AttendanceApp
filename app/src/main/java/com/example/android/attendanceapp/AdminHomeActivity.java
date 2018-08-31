package com.example.android.attendanceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class AdminHomeActivity extends AppCompatActivity {

    Button button5;
    Button button6;
    Button button7;
    Button button8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        button5=findViewById(R.id.button5);
        button5=findViewById(R.id.button6);
        button5=findViewById(R.id.button7);
        button5=findViewById(R.id.button8);

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminHomeActivity.this,
                        RoomCreationActivity.class);
                startActivity(intent);

            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(AdminHomeActivity.this,
                        AddMemberActivity.class);
                startActivity(intent2);

            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent3 = new Intent(AdminHomeActivity.this,
                       AdminNotifyActivity.class);
                startActivity(intent3);

            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent4 = new Intent(AdminHomeActivity.this,
                        ChangePasswordActivity.class);
                startActivity(intent4);

            }
        });


    }
}
