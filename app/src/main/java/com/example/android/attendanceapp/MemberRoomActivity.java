package com.example.android.attendanceapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.Locale;

public class MemberRoomActivity extends AppCompatActivity {

   private static final String TAG = MainActivity.class.getSimpleName();

    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;


    private FusedLocationProviderClient mFusedLocationClient;

    protected Location mLastLocation;


    TextView orgName;
    TextView textView11;
    TextView agenda;
    TextView attendance;
    double lat,lng;

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("https://attendance-socket.herokuapp.com/");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private Emitter.Listener onStatus = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            MemberRoomActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    Boolean inRange;
                    JSONObject details;

                    try{
                        if(data.getBoolean("connected")) {

                            textView11.setVisibility(View.INVISIBLE);
                            inRange = data.getBoolean("inRange");
                            details = data.getJSONObject("details");
                            if (inRange.equals(true)) {
                                agenda.setText("Within Range");
                            } else {
                                agenda.setText("Out of Range");
                            }
                            orgName.setText(details.getString("org").toUpperCase());
                            agenda.setVisibility(View.VISIBLE);
                            orgName.setVisibility(View.VISIBLE);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Failed to connect to lobby", Toast.LENGTH_SHORT).show();
                        }
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };


    private Emitter.Listener onConnectionError = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            MemberRoomActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_SHORT).show();
                    textView11.setVisibility(View.VISIBLE);
                }
            });
        }
    };

    private Emitter.Listener onLobbyClosed = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            MemberRoomActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "Lobby closed by admin", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MemberRoomActivity.this,
                            MemberLoginActivity.class);
                    startActivity(intent);
                }
            });
        }
    };

    private Emitter.Listener onNewMem = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            MemberRoomActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String reg;
                    try{

                        reg = data.getString("reg");
                        Toast.makeText(getApplicationContext(), "New Member: "+reg, Toast.LENGTH_SHORT).show();

                    }catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    };

    private Emitter.Listener onDis = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            MemberRoomActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String reg;
                    try{

                        reg = data.getString("reg");
                        Toast.makeText(getApplicationContext(), "Member Disconnected: "+reg, Toast.LENGTH_SHORT).show();

                    }catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    };

    @Override
    public void onStart() {
        super.onStart();

        if (!checkPermissions()) {
            requestPermissions();
        } else {
            getLastLocation();
        }
    }

    @SuppressWarnings("MissingPermission")
    private void getLastLocation() {
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location!=null) {
                            mLastLocation = location;

                            lat = mLastLocation.getLatitude();
                            lng = mLastLocation.getLongitude();
                            attendance.setText(String.format(Locale.ENGLISH,"lat: %f, lng: %f",lat,lng));
                            attendance.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(getApplicationContext(), "No last location found", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(MemberRoomActivity.this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                REQUEST_PERMISSIONS_REQUEST_CODE);
    }

    private void requestPermissions() {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION);

        if (shouldProvideRationale) {
            Toast.makeText(getApplicationContext(), "rationale to be provided", Toast.LENGTH_SHORT).show();
            startLocationPermissionRequest();

        } else {
            Log.i(TAG, "Requesting permission");

            startLocationPermissionRequest();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        Log.i(TAG, "onRequestPermissionResult");
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length <= 0) {

                Log.i(TAG, "User interaction was cancelled.");
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                getLastLocation();
            } else {
                Intent intent = new Intent();
                intent.setAction(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package",
                        BuildConfig.APPLICATION_ID, null);
                intent.setData(uri);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);



            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_room);

        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);

            boolean enabled = service
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);


        if (!enabled) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }

        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        mSocket.on("connectionErr",onConnectionError).on("status",onStatus).on("newMem",onNewMem).on("userDis",onDis).on("lobbyClosed",onLobbyClosed);
        mSocket.connect();

        JSONObject memcon = new JSONObject();
        JSONObject location = new JSONObject();

        try {
            memcon.put("org","acm");
            memcon.put("reg","17BCE2411");
            location.put("lat",12345);
            location.put("lng",12345);
            memcon.put("pos",location);

        }catch(JSONException e){
            e.printStackTrace();
        }
        orgName = findViewById(R.id.textView10);

        agenda = findViewById(R.id.textView7);
        attendance = findViewById(R.id.textView8);
        textView11 = findViewById(R.id.textView11);


        mSocket.emit("memConnect",memcon);
        mSocket.emit("status");


    }
}
