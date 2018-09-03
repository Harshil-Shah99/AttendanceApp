package com.example.android.attendanceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MemberRoomActivity extends AppCompatActivity {

    Button button15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_room);

        button15=findViewById(R.id.button15);

        button15.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MemberRoomActivity.this,
                        MemberLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
