package com.example.rathods.schoolapp_v4;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.AuthConfig;
import com.digits.sdk.android.Digits;
import com.digits.sdk.android.DigitsAuthButton;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.example.rathods.schoolapp_v4.User.UserHome;
import com.example.rathods.schoolapp_v4.utility.WebConfig;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener {

    public static final String LOGIN_URL = "http://schoolmanagementapp.16mb.com/android_sms/request_sms.php";

    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_MOBILE = "mobile";

    EditText Mobile;
    Button Login, temp;

    RadioButton radioTeacher, radioParent;
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferencesFCM;
    String TAG = "Login";

    private DigitsAuthButton digitsAuthButton;
    private AuthCallback callback;
    private AuthCallback authCallback;

    ProgressDialog progressDialog;
    String Userid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        digitsAuthButton = (DigitsAuthButton) findViewById(R.id.auth_button);

        Mobile = (EditText) findViewById(R.id.Et_Mobile);


        Login = (Button) findViewById(R.id.Btn_Login);

        radioTeacher = (RadioButton) findViewById(R.id.radioTeacher);
        radioParent = (RadioButton) findViewById(R.id.radioParent);

        sharedPreferences = getSharedPreferences("schoolapp", Context.MODE_PRIVATE);
        sharedPreferencesFCM = getSharedPreferences("schoolapp_FCM", Context.MODE_PRIVATE);
        Login.setOnClickListener(this);


        authCallback = new AuthCallback() {

            @Override
            public void success(DigitsSession session, String phoneNumber) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sharedPreferences.edit().putString("user_id", Userid).commit();
                        startActivity(new Intent(Login.this, UserHome.class));
                    }
                }, 500);
            }

            @Override
            public void failure(DigitsException exception) {

            }
        };
        digitsAuthButton.setAuthTheme(R.style.CustomDigitsTheme);

    }

    @Override
    public void onClick(View v) {
        if (v == Login) {
            if (Mobile.getText().length() == 10 && (radioParent.isChecked() == true || radioTeacher.isChecked() == true)) {

                authenticateUser();
            } else {
                Snackbar.make(findViewById(android.R.id.content), "Inputs are Not Valid!! Please Try Again..", Snackbar.LENGTH_LONG)
                        .setActionTextColor(Color.GREEN)
                        .show();
            }

        }


    }


    private void authenticateUser() {
        progressDialog = ProgressDialog.show(this, "Loading", "Please wait for a moment...");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, WebConfig.LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.d(TAG, "onResponse: " + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            if (jsonObject.has("status") && jsonObject.getInt("status") == 200) {
                                JSONObject dataObject = jsonObject.getJSONObject("data");
                                Userid = dataObject.getString("id");

                                String active = dataObject.getString("active");
                                if (active.equalsIgnoreCase("1")) {

                                    Digits.logout();
                                    AuthConfig.Builder authConfigBuilder = new AuthConfig.Builder()
                                            .withAuthCallBack(authCallback)
                                            .withPhoneNumber("+91" + Mobile.getText().toString().trim());
                                    Digits.authenticate(authConfigBuilder.build());
                                } else {
                                    Snackbar.make(findViewById(android.R.id.content), "User has been deactivated from admin.", Snackbar.LENGTH_LONG)
                                            .setActionTextColor(Color.GREEN)
                                            .show();
                                }
                            } else {
                                Snackbar.make(findViewById(android.R.id.content), jsonObject.getString("message"), Snackbar.LENGTH_LONG)
                                        .setActionTextColor(Color.GREEN)
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(Login.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(WebConfig.PARAM_USER_TOKEN, sharedPreferencesFCM.getString("device_token", ""));
                params.put(WebConfig.PARAM_MOBILE_NUMBER, Mobile.getText().toString().trim());
                params.put(WebConfig.PARAM_TYPE, (radioParent.isChecked() ? "1" : "2"));
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    public Activity getActivity() {
        return Login.this;
    }
}
