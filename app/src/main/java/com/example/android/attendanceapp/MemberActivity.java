package com.example.android.attendanceapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MemberActivity extends AppCompatActivity {

    Button membersubmitbutton;
    EditText membertoken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        membersubmitbutton=findViewById(R.id.membersubmitbutton);
        membertoken=findViewById(R.id.membertoken);

        membersubmitbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(membertoken.getText().toString().equals("0000"))
                {
                    Toast.makeText(getApplicationContext(), "Right password", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Wrong password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
