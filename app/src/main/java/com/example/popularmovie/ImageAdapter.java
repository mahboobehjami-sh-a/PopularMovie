package com.example.popularmovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>  {

    Context context;
    ArrayList<Movies> totalDetailMovie;
    ClickPosterListener clickPosterListener;

    public ImageAdapter(Context context,ArrayList<Movies> totalDetailMovie,ClickPosterListener clickPosterListener){
        this.context=context;
        this.totalDetailMovie=totalDetailMovie;
        this.clickPosterListener=clickPosterListener;
    }



    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.image_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder holder, int position) {
        Movies movies=totalDetailMovie.get(position);
        holder.tvtitle.setText(totalDetailMovie.get(position).getTitle());
        Glide.with(context).load(movies.getPoster_Path()).centerCrop().placeholder(R.drawable.one).into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return totalDetailMovie.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvtitle;

        ImageView imgPoster;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvtitle=itemView.findViewById(R.id.tvconnect);
            imgPoster=itemView.findViewById(R.id.imgPoster);
            itemView.setOnClickListener(new  View.OnClickListener(){
                @Override
                public void onClick(View v){
                    clickPosterListener.onClickPoster(totalDetailMovie.get(getAdapterPosition()),imgPoster);
                }
            });

        }
    }
}
