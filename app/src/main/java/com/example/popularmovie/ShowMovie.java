package com.example.popularmovie;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
public class ShowMovie extends AppCompatActivity {
    public ProgressBar progressBar;
    public TextView tvname,tvdirector,tvstory;
    public ImageView ivposter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressBar = (ProgressBar) findViewById(R.id.progress_circular_home);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);

        ivposter = (ImageView) findViewById(R.id.poster);
        String urlPoster=getIntent().getExtras().getString("");
        Glide.with(this).load(urlPoster).into(ivposter);

        tvname = (TextView) findViewById(R.id.name);
        String urlName=getIntent().getExtras().getString("");
        tvname.setText(urlName);

        tvdirector = (TextView) findViewById(R.id.director);
        String urlDirector=getIntent().getExtras().getString("");
        tvdirector.setText(urlDirector);

        tvstory = (TextView) findViewById(R.id.story);
        String urlStory=getIntent().getExtras().getString("");
        tvstory.setText(urlStory);
    }
}


