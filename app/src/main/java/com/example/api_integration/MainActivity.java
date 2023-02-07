   package com.example.api_integration;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

   public class MainActivity extends AppCompatActivity {
    private EditText etLoginid;
    private EditText etLoginpassword;
    private AppCompatButton btnLogin;
    private TextView btnSignup;
    String url="http://localhost/api/myapi.php/login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLoginid = (EditText) findViewById(R.id.et_loginid);
        etLoginpassword = (EditText) findViewById(R.id.et_loginpassword);
        btnLogin = (AppCompatButton) findViewById(R.id.btn_login);
        btnSignup = (TextView) findViewById(R.id.btn_signup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login_new=etLoginid.getText().toString().trim();
                String password_new=etLoginpassword.getText().toString().trim();
                if(login_new.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Login id can't be empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(password_new.equals(""))
                    {
                        Toast.makeText(MainActivity.this, "Password can't be empty", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        checkLogin(login_new, password_new);
                        etLoginid.setText("");
                        etLoginpassword.setText("");
                    }
                }
            }
        });
    }


       private void checkLogin(String login_new, String password_new) {

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
               @Override
               public void onResponse(String response) {
                   try {
                       Toast.makeText(MainActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                       Intent intent=new Intent(MainActivity.this,Showdata.class);
                       intent.putExtra("key-response",response);
                       startActivity(intent);
                   }
                   catch (Exception e)
                   {
                       e.printStackTrace();
                   }
               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {
                   error.printStackTrace();
               }
           }){
               @Nullable
               @Override
               protected Map<String, String> getParams() throws AuthFailureError {
                   HashMap<String,String>hashMap=new HashMap<>();
                   hashMap.put("key_email",login_new);
                   hashMap.put("key_password",password_new);
                   return hashMap;
               }
           };
        requestQueue.add(stringRequest);
       }
   }