package com.example.android.attendanceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RoomCreationActivity extends AppCompatActivity {

    Button button11;
    EditText editText3;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_creation);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");

        button11=findViewById(R.id.button11);
        editText3 = findViewById(R.id.editText3);
        button11.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RoomCreationActivity.this,
                        AdminRoomActivity.class);
                String array[] = new String[2];
                array[0] = name;
                array[1] = editText3.getText().toString().trim();
                editText3.setText("");
                intent.putExtra("agenda",array);
                startActivity(intent);
            }
        });
    }
}
