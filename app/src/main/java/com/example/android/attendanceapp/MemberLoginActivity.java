package com.example.android.attendanceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MemberLoginActivity extends AppCompatActivity {

    Button button13;
    EditText orgId;
    EditText username;
    int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_login);


        orgId = findViewById(R.id.editText);
        username = findViewById(R.id.editText8);
        button13=findViewById(R.id.button13);




        button13.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                /*String[] creds = new String[3];
                creds[0] = orgname;
                creds[1] = username.getText().toString().trim();
                creds[2] = passwd.getText().toString().trim();*/

                orgId.setText("");
                username.setText("");
                Intent intent = new Intent(MemberLoginActivity.this,
                        MemberRoomActivity.class);
                //intent.putExtra("creds",creds);
                startActivity(intent);
            }
        });






    }
}
