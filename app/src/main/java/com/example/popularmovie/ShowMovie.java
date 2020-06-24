package com.example.popularmovie;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class ShowMovie extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView tvname,tvdirector,tvstory;
    private ImageView ivposter;
    ShowMovie mHost ;
    String path;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.activity_show_movie, container, false);
        tvname = root.findViewById(R.id.name);
        tvdirector = root.findViewById(R.id.director);
        tvstory = root.findViewById(R.id.story);
        ivposter = root.findViewById(R.id.poster);
        progressBar = root.findViewById(R.id.progress_circular_home);
        final String urlposter = "https://image.tmdb.org/t/p/original/"+ path ;

        Glide.with(this).load(urlposter).into(ivposter);

        //Call Volley
        getData();
        return root;
    }



    private void getData() {
     RequestQueue queue = Volley.newRequestQueue(getActivity());
     String url = "https://api.themoviedb.org/3/movie/popular?api_key=2b6180dd56ad2250ddaf4ebf328fec1e" ;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);

                try {
                    JSONObject jsonObject = new JSONObject(response.toString());

                    tvname.setText(jsonObject.getString("original_title"));
                    tvdirector.setText(jsonObject.getString("charecter"));
                    tvstory.setText(jsonObject.getString("title"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Log.d( "Error Response", error.toString());
            }
        });

        queue.add(stringRequest);
    }

    @Nullable
    final public ShowMovie getActivity() {
        return mHost == null ? null : (ShowMovie) mHost.getActivity();
    }
}

