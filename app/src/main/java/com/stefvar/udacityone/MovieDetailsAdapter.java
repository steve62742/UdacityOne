package com.stefvar.udacityone;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.stefvar.udacityone.DAO.Movies.MovieDetailsDAO;

/**
 * Created by steve62742 on 15/7/2016.
 */
public class MovieDetailsAdapter extends RecyclerView.Adapter {

    private static final int TYPE_NORMAL = 1000;
    private static final int TYPE_HEADER = 2000;

    private int count = 4;
    private MovieDetailsDAO movie;
    private Context ctx;
    private MovieDetailsAdapterInterface moviedetailsAdapterInterface;

    public interface MovieDetailsAdapterInterface {
        void getClickedImage(int position, View v, int type);
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            HeaderViewHolder headerview = new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.header, parent, false));
            return headerview;
        }else{
            MyViewHolder myview = new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
            return myview;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_NORMAL;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0){
            HeaderViewHolder test = (HeaderViewHolder) holder;
            test.changePic(movie.getImages().getBackdrops().get(position).getFilePath());
        }else{
            MyViewHolder test = (MyViewHolder) holder;
            test.changePic(movie.getImages().getBackdrops().get(position).getFilePath());
            test.setPosition(position);
        }
    }

    @Override
    public int getItemCount() {
        return count;
    }

    public void setItemCount(Integer count){
        this.count = count;
    }


    public void getMovie(MovieDetailsDAO currentMovie) {
        this.movie=currentMovie;
    }

    public void setContext(Context context) {
        this.ctx = context;
    }
    public void setInterface(MovieDetailsAdapterInterface intf) {
        this.moviedetailsAdapterInterface = intf;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder  {

        ImageView image;

        Integer position;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image_inlayout);


            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    moviedetailsAdapterInterface.getClickedImage(position, v , 0);


                }
            });
        }
        public void changePic (String picURL){
            Picasso.with(itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w300/"+picURL)
                    .error(R.drawable.error)
                    .placeholder(R.drawable.placeholder)
                    .fit()
                    .centerCrop()
                    .into(image);
        }
        public void setPosition(Integer pos){
            position = pos;
        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.main_header_icon);
        }
        public void changePic (String picURL){
            Picasso.with(itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w780/"+picURL)
                    .error(R.drawable.error)
                    .placeholder(R.drawable.placeholder)
                    .fit()
                    .centerCrop()
                    .into(image);
        }
    }

}
