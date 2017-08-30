package com.example.rathods.schoolapp_v4.User.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.rathods.schoolapp_v4.R;

import com.example.rathods.schoolapp_v4.User.Models.ModelEvents;
import com.example.rathods.schoolapp_v4.User.Models.ModelNews;

import java.util.ArrayList;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.MyViewHolder> {

    private ArrayList<ModelEvents> modelEventsArrayList;
    private Activity activity;


    public EventsAdapter(ArrayList<ModelEvents> moviesList, Activity activity) {
        this.modelEventsArrayList = moviesList;
        this.activity = activity;
    }

    public ArrayList<ModelEvents> getModelNewsArrayList() {
        return modelEventsArrayList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.raw_events, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.Events_title.setText(modelEventsArrayList.get(position).getTitle());
        holder.Event_sdate.setText(modelEventsArrayList.get(position).getSdate());
        holder.Event_edate.setText(modelEventsArrayList.get(position).getEdate());


    }

    @Override
    public int getItemCount() {
        return modelEventsArrayList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView Events_title, Event_sdate, Event_edate, Event_Orgazisedby, Event_Venue;
        LinearLayout ly_details;

        public MyViewHolder(View view) {
            super(view);
            Events_title = (TextView) view.findViewById(R.id.Events_title);
            Event_sdate = (TextView) view.findViewById(R.id.Event_sdate);
            Event_edate = (TextView) view.findViewById(R.id.Event_edate);


        }
    }
}