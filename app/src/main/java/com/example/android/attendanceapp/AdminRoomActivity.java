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
import android.widget.Button;
import android.widget.EditText;
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

public class AdminRoomActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;


    private FusedLocationProviderClient mFusedLocationClient;

    protected Location mLastLocation;

    JSONObject adminobject = new JSONObject();
    JSONObject location = new JSONObject();
    double lat,lng;
    int i;

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
            AdminRoomActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    JSONObject orgDetails;
                    Boolean connected;
                    String orgId;
                    try{
                        connected = data.getBoolean("connected");
                        orgDetails = data.getJSONObject("details");
                        orgId = orgDetails.getString("org");

                        if(connected) {
                            Toast.makeText(getApplicationContext(), data.get("type").toString()+" connected", Toast.LENGTH_SHORT).show();
                            textView3.setText(orgId.toUpperCase());
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Not connected", Toast.LENGTH_SHORT).show();
                        }
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };


    private Emitter.Listener onNewMem = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            AdminRoomActivity.this.runOnUiThread(new Runnable() {
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
            AdminRoomActivity.this.runOnUiThread(new Runnable() {
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



    private Emitter.Listener onConnectionError = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            AdminRoomActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_SHORT).show();
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
                            textView4.setText(String.format(Locale.ENGLISH,"Latitude: %f",lat));
                            textView5.setText(String.format(Locale.ENGLISH,"Longitude: %f",lng));

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
        ActivityCompat.requestPermissions(AdminRoomActivity.this,
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

    Button button10;
    TextView textView2;
    TextView textView4;
    TextView textView5;
    Button button;
    EditText editText2;
    TextView textView3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_room);
        Intent intent2 = getIntent();
        final String[] agenda = intent2.getStringArrayExtra("agenda");

        i=0;

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

        mSocket.on("connectionErr",onConnectionError).on("newMem",onNewMem).on("userDis",onDis).on("status",onStatus);
        mSocket.connect();

        try{

            adminobject.put("org","acm");
            adminobject.put("passwd","qwerty");
            adminobject.put("threshold",50);
            location.put("lat",12345);//Make these as double type at backend.
            location.put("lng",12345);//
            adminobject.put("pos",location);
            adminobject.put("token",1234);
        }catch (JSONException e){
            e.printStackTrace();
        }

        mSocket.emit("adminConnect",adminobject);
        mSocket.emit("status");



        button10 = findViewById(R.id.button10);
        textView2 = findViewById(R.id.textView2);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        button = findViewById(R.id.button);
        editText2 = findViewById(R.id.editText2);
        textView3 = findViewById(R.id.textView3);

        textView2.setText(agenda[1]);


        button10.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                mSocket.emit("markPresent");
                Toast.makeText(getApplicationContext(), "Attendance Marked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminRoomActivity.this,
                        AdminHome2Activity.class);
                intent.putExtra("org",agenda[0]);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

               if(i==0){
                   editText2.setVisibility(View.VISIBLE);
                   i=1;
               }
               else if(i==1){
                   int newDist = Integer.parseInt(editText2.getText().toString());
                   if(!editText2.getText().toString().equals("")){
                    mSocket.emit("updateThreshold",newDist);
                    editText2.setText("");
                    editText2.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Range Updated", Toast.LENGTH_SHORT).show();
                    i=0;
                   }
                   else{
                       Toast.makeText(getApplicationContext(), "Enter valid value", Toast.LENGTH_SHORT).show();
                   }

               }
            }
        });
    }
}
