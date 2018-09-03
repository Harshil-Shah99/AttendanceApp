package com.example.android.attendanceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AddMemberActivity extends AppCompatActivity {

    Button button16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        button16=findViewById(R.id.button16);

        button16.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AddMemberActivity.this,
                        AddMemberActivity.class);
                startActivity(intent);
            }
        });
    }
}
