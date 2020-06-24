package com.example.popularmovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {

    ArrayList<Movies> posters_urlForImage;

    private int[] image_src={};
    Context ctx;


     ImageAdapter(Context ctx,ArrayList<Movies> posters_urlForImage){
        this.ctx=ctx;
        this.posters_urlForImage=posters_urlForImage;
    }

    @Override
    public int getCount() {
        return posters_urlForImage.size();
    }

    @Override
    public Object getItem(int position) { return posters_urlForImage.get(position); }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridView=convertView;
        if(gridView==null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridView=inflater.inflate(R.layout.image_layout,null);

        }
        ImageView imageView=(ImageView)gridView.findViewById((R.id.poster));
        imageView.setImageResource(image_src[position]);
        return gridView;
    }
}
