package com.example.android.attendanceapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AdminStatsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Meeting> meetings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_stats);
        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(meetings);
        mRecyclerView.setAdapter(mAdapter);

        initializeData();
        initializeAdapter();
    }

    private void initializeData(){

        meetings = new ArrayList<>();
        meetings.add(new Meeting("Meeting 1", Calendar.getInstance().getTime().toString()));
        meetings.add(new Meeting("Meeting 2", Calendar.getInstance().getTime().toString()));
        meetings.add(new Meeting("Meeting 3", Calendar.getInstance().getTime().toString()));
    }

    private void initializeAdapter(){
        MyAdapter adapter = new MyAdapter(meetings);
        mRecyclerView.setAdapter(adapter);
    }

}