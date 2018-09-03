package com.example.android.attendanceapp;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.PersonViewHolder>{


    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView agenda;
        TextView date;


        PersonViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            agenda = itemView.findViewById(R.id.Agenda);
            date = itemView.findViewById(R.id.Date);
        }
    }

    List<Meeting> meetings;

    MyAdapter(List<Meeting> meetings){
        this.meetings = meetings;
    }


    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_card_view, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public int getItemCount() {
        return meetings.size();
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.agenda.setText(meetings.get(i).agenda);
        personViewHolder.date.setText(meetings.get(i).date);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}