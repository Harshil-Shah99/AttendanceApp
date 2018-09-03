package com.example.android.attendanceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class RoomCreationActivity extends AppCompatActivity {

    Button button11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_creation);

        button11=findViewById(R.id.button11);

        button11.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RoomCreationActivity.this,
                        AdminRoomActivity.class);
                startActivity(intent);
            }
        });
    }
}
