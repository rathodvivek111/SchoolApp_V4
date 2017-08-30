package com.example.rathods.schoolapp_v4.User;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rathods.schoolapp_v4.R;
import com.example.rathods.schoolapp_v4.User.Adapters.ImageAdapter;
import com.example.rathods.schoolapp_v4.User.Adapters.PicturesAdapter;
import com.example.rathods.schoolapp_v4.User.Models.ModelPictures;
import com.example.rathods.schoolapp_v4.utility.WebConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GalleryPagingActivity extends AppCompatActivity {

    String TAG = getClass().getSimpleName();
    ViewPager viewPager;
    ImageAdapter imageAdapter;
    ArrayList<ModelPictures> list = new ArrayList<>();
    int POSITION = 0;
    ImageView imgCross, imgDownload;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallary_paging);
        if (getIntent().getExtras() != null) {
            list = (ArrayList<ModelPictures>) getIntent().getSerializableExtra("images_list");
            POSITION = getIntent().getIntExtra("position", 0);
        }
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        imgCross = (ImageView) findViewById(R.id.imgCross);
        imgCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imgDownload = (ImageView) findViewById(R.id.imgDownload);
        imgDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = ProgressDialog.show(GalleryPagingActivity.this, "Loading...", "Image is Downloading. Please wait...");
                RequestQueue requestQueue = Volley.newRequestQueue(GalleryPagingActivity.this);
                imageVolleyRequest(list.get(viewPager.getCurrentItem()).getUrl().toString(), requestQueue);
            }
        });
        imageAdapter = new ImageAdapter(this, list);
        viewPager.setAdapter(imageAdapter);
        viewPager.setCurrentItem(POSITION);
    }


    private RequestQueue imageVolleyRequest(String url,
                                            RequestQueue imageRequest) {
        imageRequest.add(new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                Log.d(TAG, "onResponse: ");
                SaveImage(bitmap);
            }
        }, 1028, 1028, Bitmap.Config.ARGB_8888,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("", "http Volley request failed!", volleyError);
                    }
                }
        ));
        return imageRequest;
    }


    private void SaveImage(Bitmap finalBitmap) {
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/SchoolApp");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-" + n + ".jpg";
        File file = new File(myDir, fname);
        if (file.exists()) file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            progressDialog.dismiss();
            Toast.makeText(this, "Image is Downloaded...", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
