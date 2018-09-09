package com.example.android.attendanceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class AdminLoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Button button,button2;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        Spinner dropdown = findViewById(R.id.spinner1);

        String[] items = new String[]{"Choose Organization", "Uno", "Dos", "Tres"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        dropdown.setAdapter(adapter);

        button=findViewById(R.id.button);
        button2=findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminLoginActivity.this,
                        AdminHome2Activity.class);
                startActivity(intent);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminLoginActivity.this,
                        NewOrganizationActivity.class);
                startActivity(intent);

            }
        });
    }
}
