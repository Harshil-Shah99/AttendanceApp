package com.example.android.attendanceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AdminRoomActivity extends AppCompatActivity {

    Button button10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_room);

        button10=findViewById(R.id.button10);

        button10.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminRoomActivity.this,
                        AdminHome2Activity.class);
                startActivity(intent);
            }
        });
    }
}
