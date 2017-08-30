package com.example.rathods.schoolapp_v4.User;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rathods.schoolapp_v4.R;
import com.example.rathods.schoolapp_v4.User.Adapters.PicturesAdapter;
import com.example.rathods.schoolapp_v4.User.Models.ModelPictures;
import com.example.rathods.schoolapp_v4.utility.WebConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GalleryDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    String TAG = getClass().getSimpleName();
    RecyclerView rcvGallery;
    ProgressBar progressbar;
    PicturesAdapter picturesAdapter;
    String GALLERY_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallary_details);

        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        if (getIntent().getExtras() != null) {
            GALLERY_ID = getIntent().getStringExtra("gallery_id");
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Gallery");
        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        rcvGallery = (RecyclerView) findViewById(R.id.rcvPictures);
        rcvGallery.setLayoutManager(new GridLayoutManager(this, 2));
        getPictures();
    }

    @Override
    public void onClick(View v) {

    }


    private void getPictures() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, WebConfig.GETPICTURES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "onResponse: " + response);
                        progressbar.setVisibility(View.GONE);
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            JSONArray dataArray = jsonObject.getJSONArray("data");
                            ArrayList<ModelPictures> list = new ArrayList<>();
                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject mainObject = dataArray.getJSONObject(i);
                                ModelPictures modelPictures = new ModelPictures();
                                modelPictures.setUrl(mainObject.getString("filename"));
                                list.add(modelPictures);
                            }
                            picturesAdapter = new PicturesAdapter(list, GalleryDetailsActivity.this);
                            rcvGallery.setAdapter(picturesAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GalleryDetailsActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(WebConfig.PARAM_GALLERY_ID, GALLERY_ID);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }


}
