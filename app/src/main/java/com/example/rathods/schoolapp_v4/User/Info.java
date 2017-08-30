package com.example.rathods.schoolapp_v4.User;

/**
 * Created by RATHOD'S on 6/7/2017.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rathods.schoolapp_v4.R;
import com.example.rathods.schoolapp_v4.utility.WebConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Info extends Fragment {

    TextView tvFName, tvFNumber, tvMName, tvMNumber, tvCName1, tvStandard1, tvDivision1, tvgrno1;
    String TAG = getClass().getSimpleName();
    SharedPreferences sharedPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_info, container, false);
        sharedPreferences = getActivity().getSharedPreferences("schoolapp", Context.MODE_PRIVATE);

        tvFName = (TextView) rootView.findViewById(R.id.tvFName);
        tvFNumber = (TextView) rootView.findViewById(R.id.tvFNumber);
        tvMName = (TextView) rootView.findViewById(R.id.tvMName);
        tvMNumber = (TextView) rootView.findViewById(R.id.tvMNumber);
        tvCName1 = (TextView) rootView.findViewById(R.id.tvCName1);
        tvStandard1 = (TextView) rootView.findViewById(R.id.tvStandard1);
        tvDivision1 = (TextView) rootView.findViewById(R.id.tvDivision1);
        tvgrno1 = (TextView) rootView.findViewById(R.id.tvgrno1);
        getData();
        return rootView;

    }

    private void getData() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, WebConfig.GETPARENTINFO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "onResponse: ");
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int STATUS = jsonObject.getInt("status");
                            if (STATUS == 200) {
                                JSONObject dataObject = jsonObject.getJSONObject("data");
                                tvFName.setText(dataObject.getString("father_name"));
                                tvFNumber.setText(dataObject.getString("father_mobile"));
                                tvMName.setText(dataObject.getString("mother_name"));
                                tvMNumber.setText(dataObject.getString("mother_mobile"));
                                JSONArray Childerns = dataObject.getJSONArray("childrens");

                                JSONObject Childern1 = Childerns.getJSONObject(0);
                                tvCName1.setText(Childern1.getString("student_name"));
                                tvStandard1.setText(Childern1.getString("current_std"));
                                tvDivision1.setText(Childern1.getString("division"));
                                tvgrno1.setText(Childern1.getString("gr_no"));
                            } else {
                                Toast.makeText(getActivity(), "" + jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse: ");
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("parent_id", sharedPreferences.getString("user_id", "1"));
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}
