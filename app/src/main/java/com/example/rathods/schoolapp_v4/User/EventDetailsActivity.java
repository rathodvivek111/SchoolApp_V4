package com.example.rathods.schoolapp_v4.User;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.rathods.schoolapp_v4.R;
import com.example.rathods.schoolapp_v4.User.Adapters.ImageAdapter;
import com.example.rathods.schoolapp_v4.User.Models.ModelPictures;

import java.util.ArrayList;

public class EventDetailsActivity extends AppCompatActivity {

    String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallary_paging);
    }
}
