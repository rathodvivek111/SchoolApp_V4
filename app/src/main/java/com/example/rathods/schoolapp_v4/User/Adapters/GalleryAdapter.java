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
import com.example.rathods.schoolapp_v4.User.Models.ModelGallery;

import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyViewHolder> {

    private ArrayList<ModelGallery> modelGalleryArrayList;
    private Activity activity;


    public GalleryAdapter(ArrayList<ModelGallery> moviesList, Activity activity) {
        this.modelGalleryArrayList = moviesList;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.raw_gallery, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Glide
                .with(activity)
                .load(modelGalleryArrayList.get(position).getUrl())
                .placeholder(R.drawable.img_placeholder)
                .centerCrop()
                .into(holder.imgPic);
        holder.textView2.setText(modelGalleryArrayList.get(position).getTitle());
        holder.total_gal_count.setText(modelGalleryArrayList.get(position).getImage_count());
        holder.Albumcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, GalleryDetailsActivity.class);
                intent.putExtra("gallery_id", modelGalleryArrayList.get(position).getGallery_id());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelGalleryArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgPic;
        public TextView textView2,total_gal_count;
        public CardView Albumcard;;

        public MyViewHolder(View view) {
            super(view);
            imgPic = (ImageView) view.findViewById(R.id.imageView2);
            textView2 = (TextView) view.findViewById(R.id.textView2);
            total_gal_count = (TextView) view.findViewById(R.id.gal_count);
            Albumcard = (CardView) view.findViewById(R.id.Albumcard);
        }
    }
}