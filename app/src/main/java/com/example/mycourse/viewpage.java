package com.example.mycourse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class viewpage extends AppCompatActivity {
    RecyclerView recyclerView;
    List<course> mylist;
    RecyclerView.Adapter adapter;



    String api="https://dummyapilist.herokuapp.com/getcourses";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpage);

        recyclerView=(RecyclerView)findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        mylist=new ArrayList<>();
        int numOfColumn=1;
        recyclerView.setLayoutManager(new GridLayoutManager(this,numOfColumn));

        callapi();
    }

    private void callapi() {

        StringRequest stringRequest=new StringRequest(Request.Method.GET, api, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
              try {

                  JSONArray jsonArray = new JSONArray(response);
                  for(int i = 0; i < jsonArray.length(); i++)
                  {
                      JSONObject jsonObject=jsonArray.getJSONObject(i);
                      String tit=jsonObject.getString("courseTitle");
                      String des=jsonObject.getString("courseDescription");
                      String dur=jsonObject.getString("courseDuration");
                      String ven=jsonObject.getString("courseVenue");
                      String dat=jsonObject.getString("courseDate");

                      course c=new course(tit,des,dur,ven,dat);
                      mylist.add(c);

                  }
                  adapter=new custom(mylist,getApplicationContext());
                  recyclerView.setAdapter(adapter);

              }catch (JSONException e){
                  Toast.makeText(getApplicationContext(),String.valueOf(e),Toast.LENGTH_SHORT).show();
                  e.printStackTrace();}



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


}
