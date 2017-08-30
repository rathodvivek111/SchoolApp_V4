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
import com.example.rathods.schoolapp_v4.User.Models.ModelNotice;
import com.example.rathods.schoolapp_v4.utility.WebConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class showNotice extends AppCompatActivity {


    String TAG = getClass().getSimpleName();
    TextView notice_title,notice_desc,notice_date,notice_active;
    private ProgressDialog progressDialog;

    //EDIT
    private ModelNotice modelNotice;
    private boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notice);
        if (getIntent().getExtras() != null) {
            modelNotice = (ModelNotice) getIntent().getSerializableExtra("noticeModel");
            isEditMode = true;
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Notice");



        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        notice_active = (TextView) findViewById(R.id.notice_active);
        notice_date = (TextView) findViewById(R.id.notice_date);
        notice_title = (TextView) findViewById(R.id.notice_title);
        notice_desc = (TextView) findViewById(R.id.notice_desc);




        notice_active.setText(modelNotice.getActive());
        notice_date.setText(modelNotice.getDate());
        notice_title.setText(modelNotice.getTitle());
        notice_desc.setText(modelNotice.getDesc());










    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
