package com.example.android.attendanceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AdminHome2Activity extends AppCompatActivity {

    Button button3;
    Button button4;
    Button button17;
    Button button18;
    Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home2);

        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.button4);
        button17=findViewById(R.id.button17);
        button18=findViewById(R.id.button18);
        button5=findViewById(R.id.button5);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminHome2Activity.this,
                        AddMemberActivity.class);
                startActivity(intent);

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminHome2Activity.this,
                        AdminNotifyActivity.class);
                startActivity(intent);

            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminHome2Activity.this,
                        AdminStatsActivity.class);
                startActivity(intent);

            }
        });

        button17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminHome2Activity.this,
                        ChangePasswordActivity.class);
                startActivity(intent);

            }
        });

        button18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminHome2Activity.this,
                        RoomCreationActivity.class);
                startActivity(intent);

            }
        });
    }
}
