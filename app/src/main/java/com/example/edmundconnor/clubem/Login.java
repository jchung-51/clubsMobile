package com.example.edmundconnor.clubem;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class Login extends Activity  {
    Button login,signUp;
    EditText editEmail,editPw;
    String url = "https://clubs-jhu.herokuapp.com/clubs/api/login";
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button)findViewById(R.id.login);
        editEmail = (EditText)findViewById(R.id.email);
        editPw = (EditText)findViewById(R.id.pw);
        txt = (TextView) findViewById(R.id.textview2);

        signUp = (Button)findViewById(R.id.signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String password = editPw.getText().toString();
                final String email = editEmail.getText().toString();
                System.out.println(password + email );
                Log.d("params", email + " " + password);

                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("email", email);
                    jsonBody.put("password", password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.PUT, url, jsonBody,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    Log.d("response", response.toString());
                                    String id = response.get("userID").toString();
                                    Integer userId = Integer.parseInt(id);
                                    System.out.println(userId);
                                    txt.setVisibility(View.INVISIBLE);
                                    Intent intent = new Intent(getApplicationContext(), TabListActivity.class);
                                    intent.putExtra("userID", id);
                                    startActivity(intent);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                txt.setVisibility(View.VISIBLE);
                                Log.d("Error", "Error: " + error.getMessage());
                            }
                        }
                ) {
                };
                Volley.newRequestQueue(getApplicationContext()).add(putRequest);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });
    }
}