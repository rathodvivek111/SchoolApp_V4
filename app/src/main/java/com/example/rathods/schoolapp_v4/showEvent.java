package com.example.rathods.schoolapp_v4;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rathods.schoolapp_v4.R;
import com.example.rathods.schoolapp_v4.User.Models.ModelEvents;
import com.example.rathods.schoolapp_v4.utility.WebConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class showEvent extends AppCompatActivity {


    String TAG = getClass().getSimpleName();
    TextView tv_title,tv_sdate,tv_edate,tv_readmore,tv_venue,tv_theme,tv_organized_by;
    private ProgressDialog progressDialog;

    //EDIT
    private ModelEvents modelEvents;
    private boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_event);
        if (getIntent().getExtras() != null) {
            modelEvents = (ModelEvents) getIntent().getSerializableExtra("eventsModel");
            isEditMode = true;
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Event");



        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        tv_sdate = (TextView) findViewById(R.id.tv_sdate);
        tv_edate = (TextView) findViewById(R.id.tv_edate);
        tv_venue = (TextView) findViewById(R.id.tv_venue);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_readmore = (TextView) findViewById(R.id.tv_readmore);
        tv_theme = (TextView) findViewById(R.id.tv_theme);
        tv_organized_by = (TextView) findViewById(R.id.tv_organized_by);



        tv_sdate.setText(modelEvents.getSdate());
        tv_edate.setText(modelEvents.getEdate());
        tv_title.setText(modelEvents.getTitle());
        tv_readmore.setText(modelEvents.getUrl());
        tv_organized_by.setText(modelEvents.getOrganized_by());
        tv_theme.setText(modelEvents.getTheme());
        tv_venue.setText(modelEvents.getVenue());










    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
