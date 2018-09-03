package com.example.android.attendanceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MemberLoginActivity extends AppCompatActivity {

    Button button13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_login);

        button13=findViewById(R.id.button13);

        button13.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MemberLoginActivity.this,
                        MemberRoomActivity.class);
                startActivity(intent);
            }
        });

        Spinner dropdown = findViewById(R.id.spinner1);

        String[] items = new String[]{"Choose Organization", "Uno", "Dos", "Tres"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        dropdown.setAdapter(adapter);
    }
}
