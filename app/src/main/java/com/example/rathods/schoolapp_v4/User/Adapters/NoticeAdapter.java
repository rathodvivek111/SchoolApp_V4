package com.example.rathods.schoolapp_v4.User.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rathods.schoolapp_v4.R;
import com.example.rathods.schoolapp_v4.User.GalleryDetailsActivity;
import com.example.rathods.schoolapp_v4.User.Models.ModelEvents;
import com.example.rathods.schoolapp_v4.User.Models.ModelNotice;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.MyViewHolder> {

    private ArrayList<ModelNotice> modelNoticeArrayList;
    private Activity activity;


    public NoticeAdapter(ArrayList<ModelNotice> moviesList, Activity activity) {
        this.modelNoticeArrayList = moviesList;
        this.activity = activity;
    }

    public ArrayList<ModelNotice> getModelNoticeArrayList() {
        return modelNoticeArrayList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.raw_notice, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.Notice_title.setText(modelNoticeArrayList.get(position).getTitle());
        holder.Notice_date.setText(modelNoticeArrayList.get(position).getDate());
        holder.Notice_desc.setText(modelNoticeArrayList.get(position).getDesc());

    }

    @Override
    public int getItemCount() {
        return modelNoticeArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView Notice_desc,Notice_title,Notice_date;


        public MyViewHolder(View view) {
            super(view);
            Notice_desc = (TextView) view.findViewById(R.id.Notice_desc);
            Notice_title = (TextView) view.findViewById(R.id.Notice_title);
            Notice_date = (TextView) view.findViewById(R.id.Notice_date);
        }
    }
}