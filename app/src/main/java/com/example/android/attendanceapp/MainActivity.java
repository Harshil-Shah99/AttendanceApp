package com.example.android.attendanceapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    Button adminbutton;
    Button memberbutton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        adminbutton = findViewById(R.id.loginbutton);
        memberbutton = findViewById(R.id.memberlogin);


        adminbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(MainActivity.this,
                            AdminLoginActivity.class);
                    startActivity(intent);

            }
        });



        memberbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,
                        MemberLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}