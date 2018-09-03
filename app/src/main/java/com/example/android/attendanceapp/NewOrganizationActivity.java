package com.example.android.attendanceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class NewOrganizationActivity extends AppCompatActivity {

    Button button14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_organization);

        button14=findViewById(R.id.button14);

        button14.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(NewOrganizationActivity.this,
                        AdminLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
