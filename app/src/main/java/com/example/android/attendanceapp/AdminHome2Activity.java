package com.example.android.attendanceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class AdminHome2Activity extends AppCompatActivity {



    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("https://attendance-socket.herokuapp.com/");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


    JSONObject adminobject = new JSONObject();
    JSONObject location = new JSONObject();
    Button button3;
    Button button4;
    Button button17;
    Button button18;
    Button button5;




    private Emitter.Listener onStatus = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            AdminHome2Activity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
                }
            });
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home2);
        mSocket.on("status",onStatus);
        mSocket.connect();

        try{
            adminobject.put("org","ACM");
            adminobject.put("threshold",50);
            location.put("lat",12345);
            location.put("lng",12345);
            adminobject.put("pos",location);
            adminobject.put("token",9999);
        }catch (JSONException e){
            e.printStackTrace();
        }
        mSocket.emit("adminConnect",adminobject);
        mSocket.emit("status");


        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.button4);
        button17=findViewById(R.id.button17);
        button18=findViewById(R.id.button18);
        button5=findViewById(R.id.button5);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminHome2Activity.this,
                        AddMemberActivity.class);
                startActivity(intent);

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminHome2Activity.this,
                        AdminNotifyActivity.class);
                startActivity(intent);

            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminHome2Activity.this,
                        AdminStatsActivity.class);
                startActivity(intent);

            }
        });

        button17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminHome2Activity.this,
                        ChangePasswordActivity.class);
                startActivity(intent);

            }
        });

        button18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminHome2Activity.this,
                        RoomCreationActivity.class);
                startActivity(intent);

            }
        });
    }
}
