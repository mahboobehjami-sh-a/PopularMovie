package com.example.popularmovie;

import androidx.annotation.StringDef;

public class Movies {

    String poster_path;
    String title;
    String director;
    String description;

    public Movies(String poster_path, String title, String director, String description){
        this.poster_path=poster_path;
        this.title=title;
        this.director=director;
        this.description=description;
    }
    public  String getPoster_Path(){
        return poster_path;
    }

    public  String getTitle(){
        return title;
    }

    public  String getDirector(){
        return director;
    }

    public  String getDescription(){
        return description;
    }



}
