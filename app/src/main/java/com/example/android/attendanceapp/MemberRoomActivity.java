package com.example.android.attendanceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class MemberRoomActivity extends AppCompatActivity {

    Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            MemberRoomActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String username;
                    String message;
                    try {
                        username = data.getString("username");
                        message = data.getString("message");
                    } catch (JSONException e) {
                        return;
                    }
                }
            });
        }
    };

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("https://attendance-socket.herokuapp.com");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }



    Button button15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_room);

        mSocket.on("newMessage",onNewMessage);
        mSocket.connect();






        button15=findViewById(R.id.button15);

        button15.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MemberRoomActivity.this,
                        MemberLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
