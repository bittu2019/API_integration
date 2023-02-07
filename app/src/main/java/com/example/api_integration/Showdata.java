package com.example.api_integration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Showdata extends AppCompatActivity {

    private TextView tvName;
    private TextView tvEmail;
    private TextView tvPassword;
    private TextView tvGender;
    private TextView tvCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdata);

        tvName = (TextView) findViewById(R.id.tv_name);
        tvEmail = (TextView) findViewById(R.id.tv_email);
        tvPassword = (TextView) findViewById(R.id.tv_password);
        tvGender = (TextView) findViewById(R.id.tv_gender);
        tvCity = (TextView) findViewById(R.id.tv_city);

        String data=getIntent().getStringExtra("key-response");

        try {
            JSONObject jsonObject=new JSONObject(data);
            String serverresponse=jsonObject.getString("server response");
            JSONArray jsonArray=new JSONArray(serverresponse);
            JSONObject jsonObject1=jsonArray.getJSONObject(0);
            String name=jsonObject1.getString("name");
            String email=jsonObject1.getString("email");
            String password=jsonObject1.getString("password");
            String gender=jsonObject1.getString("gender");
            String city=jsonObject1.getString("city");
            tvName.setText("Name : "+name);
            tvEmail.setText("Email : "+email);
            tvPassword.setText("Password : "+password);
            tvGender.setText("Gender : "+gender);
            tvCity.setText("City : "+city);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}