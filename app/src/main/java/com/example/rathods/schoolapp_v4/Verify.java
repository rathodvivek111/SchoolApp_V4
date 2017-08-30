package com.example.rathods.schoolapp_v4;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rathods.schoolapp_v4.User.UserHome;


import java.util.HashMap;
import java.util.Map;

public class Verify extends AppCompatActivity implements View.OnClickListener {

    public static final String VERIFY_URL = "http://schoolmanagementapp.16mb.com/android_sms/verify_otp.php";

    public static final String KEY_OTP = "otp";

    EditText et_otp;
    Button Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);



        et_otp=(EditText) findViewById(R.id.Et_Otp);


        Submit = (Button) findViewById(R.id.Btn_Submit);

        Submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == Submit)
        {
            if(et_otp.getText().toString().length()==6){
                VerifyUser();
            }else
            {
                Snackbar.make(findViewById(android.R.id.content), "Please Enter All Digits!!!", Snackbar.LENGTH_LONG)
                        .setActionTextColor(Color.GREEN)
                        .show();
            }

        }
    }

    private void VerifyUser(){

        final String otp = et_otp.getText().toString();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, VERIFY_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        if(response.contains("User created successfully!")){
                            Snackbar.make(findViewById(android.R.id.content), "User verified successfully!", Snackbar.LENGTH_LONG)
                                    .setActionTextColor(Color.GREEN)
                                    .show();
                            startActivity(new Intent(Verify.this,UserHome.class));
                        }else {


                            Snackbar.make(findViewById(android.R.id.content), "Sorry! Error occurred in Verification.", Snackbar.LENGTH_LONG)
                                    .setActionTextColor(Color.RED)
                                    .show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Verify.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_OTP,otp);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
