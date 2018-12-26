package com.cetpainfotech.todomysql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText emailid,passwordid;
    Button newaccount,login;
    String url="http://searchkero.com/aditya/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        newaccount=findViewById(R.id.newaccount);
        login=findViewById(R.id.login);
        emailid=findViewById(R.id.emailid);
        passwordid=findViewById(R.id.passwordid);

        newaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest sr=new StringRequest(1, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equalsIgnoreCase("success"))
                                {
                                    Intent intent=new Intent(LoginActivity.this,AddtaskActivity.class);
                                    startActivity(intent);
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> map=new HashMap<>();
                        map.put("emailid",emailid.getText().toString());
                        map.put("passwordid",passwordid.getText().toString());
                        return map;

                    }
                };
                //to pass the string request to requestqueue
                RequestQueue rq =Volley
                        .newRequestQueue(LoginActivity.this);
                rq.add(sr);



            }
        });
}
}