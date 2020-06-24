package com.example.popularmovie;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toolbar;

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

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    ProgressBar progressBar;
    Button button;
    public  String urlForGetPoster="https://image.tmdb.org/t/p/w500";
    private static final String TAG=MainActivity.class.getSimpleName();
    ArrayList<Movies> posters_urlForImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button) findViewById(R.id.next);
        gridView=(GridView)findViewById(R.id.gridView_HomePage);
        progressBar=(ProgressBar)findViewById(R.id.progress_circular_home);
        getDataFromAPI();
        ImageAdapter imageAdapter=new ImageAdapter(this);
        gridView.setAdapter(imageAdapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });
    }

    private void getDataFromAPI() {
        String url="https://api.themoviedb.org/3/movie/popular?api_key=2b6180dd56ad2250ddaf4ebf328fec1e";
        posters_urlForImage=new ArrayList<>();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "on Response:" + response);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject data = jsonArray.getJSONObject(i);
                            urlForGetPoster += data.getString("poster_path");
                            posters_urlForImage.add(new Movies(urlForGetPoster));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Log.e(TAG,"onResponse: " +error);
            }
        });

        Volley.newRequestQueue(this).add(stringRequest);
    }


    private void openActivity() {
        Intent intent=new Intent(this,ShowMovie.class);
        startActivity(intent);
    }


}