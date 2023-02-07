package com.example.api_integration;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    private EditText etName;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etGender;
    private EditText etCity;
    private AppCompatButton btnRegister;
    String url="http://localhost/api/myapi.php/register";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        etName = (EditText) findViewById(R.id.et_name);
        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_password);
        etGender = (EditText) findViewById(R.id.et_gender);
        etCity = (EditText) findViewById(R.id.et_city);
        btnRegister = (AppCompatButton) findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String new_name=etName.getText().toString().trim();
                String new_email=etEmail.getText().toString().trim();
                String new_password=etPassword.getText().toString().trim();
                String new_gender=etGender.getText().toString().trim();
                String new_city=etCity.getText().toString().trim();
                if(new_name.equals(""))
                {
                    Toast.makeText(SignUp.this, "Fill the Name", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(new_email.equals("")){
                        Toast.makeText(SignUp.this, "Fill the Email", Toast.LENGTH_SHORT).show();
                    }else{
                        if(new_password.equals("")){
                            Toast.makeText(SignUp.this, "Fill the Password", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            if(new_gender.equals("")){
                                Toast.makeText(SignUp.this, "Fill the Gender", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                if(new_city.equals("")){
                                    Toast.makeText(SignUp.this, "Fill the City", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    registerme(new_name,new_email,new_password,new_gender,new_city);
                                    etName.setText("");
                                    etEmail.setText("");
                                    etPassword.setText("");
                                    etGender.setText("");
                                    etCity.setText("");
                                }
                            }
                        }

                    }
                }
            }
        });

    }

    private void registerme(String new_name, String new_email, String new_password, String new_gender, String new_city) {

        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(SignUp.this, response, Toast.LENGTH_SHORT).show();
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
                hashMap.put("key_name",new_name);
                hashMap.put("key_email",new_email);
                hashMap.put("key_password",new_password);
                hashMap.put("key_gender",new_gender);
                hashMap.put("key_city",new_city);
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);

    }

}
//        key_name:Bittu
//        key_email:bittu@gmail.com
//        key_password:123456
//        key_gender:male
//        key_city:Bhagalpur