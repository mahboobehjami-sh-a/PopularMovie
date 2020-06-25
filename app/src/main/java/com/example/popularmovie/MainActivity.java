package com.example.popularmovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ClickPosterListener {

    String title;
    String director;
    String description;
    String poster_path;
    String URL_POSTERS_BASE="https://image.tmdb.org/t/p/w500";
    String URL_POPULER="https://api.themoviedb.org/3/movie/popular?api_key=2b6180dd56ad2250ddaf4ebf328fec1e";

    RecyclerView recyclerViewPoster;
    ProgressBar progressBar;
    RequestQueue requestQueue;
    ArrayList<Movies> listOfPupuler=new ArrayList<>();

    TextView textView;

    private static final String TAG=MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue=Volley.newRequestQueue(this);
        progressBar=(ProgressBar)findViewById(R.id.progress_circular_home);
        recyclerViewPoster=(RecyclerView)findViewById(R.id.rv_posters);

        textView=(TextView)findViewById(R.id.tvconnect);

        populerMpvie();

    }

    private void populerMpvie() {

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, URL_POPULER, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response != null) {
                    progressBar.setVisibility(View.GONE);
                    Log.e(TAG, "on Response:" + response);
                    try {
                        JSONArray jsonArray = response.getJSONArray("results");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject data = jsonArray.getJSONObject(i);
                                poster_path = URL_POSTERS_BASE+data.getString("poster_path");
                                title = data.getString("title");
                                description=data.getString("overview");
                                listOfPupuler.add(new Movies(poster_path,title,director,description));
                                textView.setText(title);

                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                        ShowRecyclerviewPster();

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
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    private void ShowRecyclerviewPster() {
        ImageAdapter imageAdapter=new ImageAdapter(this,listOfPupuler,this);
        recyclerViewPoster.setAdapter(imageAdapter);
        recyclerViewPoster.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

    }


    @Override
    public void onClickPoster(Movies movies, ImageView imageView) {


    }
}