package com.example.android.attendanceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminHome2Activity extends AppCompatActivity {



    String name;
    Button button18;
    Button button5;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home2);

        Intent intent = getIntent();
        name = intent.getStringExtra("org");


        button18=findViewById(R.id.button18);
        button5=findViewById(R.id.button5);
        textView=findViewById(R.id.textView);

        textView.setText(name);


        /*button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminHome2Activity.this,
                        AdminNotifyActivity.class);
                startActivity(intent);

            }
        });*/

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminHome2Activity.this,
                        AdminStatsActivity.class);
                startActivity(intent);

            }
        });



        button18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminHome2Activity.this,
                        RoomCreationActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);

            }
        });
    }
}
