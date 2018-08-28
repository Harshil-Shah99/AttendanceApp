package com.example.android.attendanceapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    EditText username;
    EditText password;
    Button adminbutton;
    Button memberbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username1);
        password = findViewById(R.id.password1);
        adminbutton = findViewById(R.id.loginbutton);
        memberbutton = findViewById(R.id.memberlogin);

        adminbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {

                    Toast.makeText(getApplicationContext(), "Right password", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,
                            AdminPagerActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong password", Toast.LENGTH_SHORT).show();
                }
            }
        });


        memberbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,
                        MemberActivity.class);
                startActivity(intent);
            }
        });
    }
}