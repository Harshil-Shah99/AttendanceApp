package com.example.android.attendanceapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

public class TokenActivity extends AppCompatActivity {

    TextView tokenholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token);

        tokenholder=findViewById(R.id.tokenholder);
        final int random = new Random().nextInt(9000) + 1000;

        tokenholder.setText(String.format(Locale.ENGLISH,"Generated token:\n %d",random));
    }
}
