package com.example.android.attendanceapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

public class CardViewActivity extends AppCompatActivity {

    TextView agenda;
    TextView date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_card_view);
        agenda = findViewById(R.id.Agenda);
        date = findViewById(R.id.Date);

        String datez = DateFormat.getDateTimeInstance()
                .format(new Date());
        agenda.setText("Meeting 1");
        date.setText(datez);

    }
}
