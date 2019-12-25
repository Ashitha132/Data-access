package com.example.mycourse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class addpage extends AppCompatActivity {
    String s1,s2,s3,s4,s5,a,be,cd,d,e;
    ProgressBar p;
    EditText e1,e2,e3,e4,e5;
    Button b;
    course c;
    String api="https://dummyapilist.herokuapp.com/addcourse";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpage);
        c=new course();
        e1=(EditText)findViewById(R.id.t);
        e2=(EditText)findViewById(R.id.des);
        e3=(EditText)findViewById(R.id.dur);
        e4=(EditText)findViewById(R.id.ven);
        e5=(EditText)findViewById(R.id.dat);
        b=(Button)findViewById(R.id.sub);
        p=(ProgressBar)findViewById(R.id.pro);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.setVisibility(View.VISIBLE);
                s1=e1.getText().toString().trim();
                s2=e2.getText().toString().trim();
                s3=e3.getText().toString().trim();
                s4=e4.getText().toString().trim();
                s5=e5.getText().toString().trim();
                c.setTitle(s1);
                c.setDescription(s2);
                c.setDuration(s3);
                c.setVenue(s4);
                c.setDate(s5);
                 a=c.getTitle();
                 be=c.getDescription();
                cd=c.getDuration();
                d=c.getVenue();
                 e=c.getDate();



                callApi();



            }
        });
    }

    private void callApi() {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                        p.setVisibility(View.INVISIBLE);
                        e1.setText("");
                        e2.setText("");
                        e3.setText("");
                        e4.setText("");
                        e5.setText("");

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        p.setVisibility(View.INVISIBLE);

                        Toast.makeText(getApplicationContext(),String.valueOf(error),Toast.LENGTH_SHORT).show();


                    }
                })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params=new HashMap<>();
                params.put("courseTitle",s1);
                params.put("courseDescription",s2);
                params.put("courseDuration",s3);
                params.put("courseVenue",s4);
                params.put("courseDate",s5);

                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
