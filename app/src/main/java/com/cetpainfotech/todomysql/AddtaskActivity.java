package com.cetpainfotech.todomysql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddtaskActivity extends AppCompatActivity {
    EditText taskname,taskdes;
    Button addtask;
    String url="http://searchkero.com/aditya/addtask.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);

        addtask = findViewById(R.id.addtask);
        taskdes = findViewById(R.id.taskdes);
        taskname = findViewById(R.id.taskname);
        addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringRequest sr = new StringRequest(1, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(AddtaskActivity.this, response, Toast.LENGTH_SHORT).show();

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();
                        map.put("taskname", taskname.getText().toString());
                        map.put("taskdes", taskdes.getText().toString());
                        return map;
                    }
                };
                //to pass the string request to requestqueue
                RequestQueue rq = Volley
                        .newRequestQueue(AddtaskActivity.this);
                rq.add(sr);

            }
        });
    }
}
