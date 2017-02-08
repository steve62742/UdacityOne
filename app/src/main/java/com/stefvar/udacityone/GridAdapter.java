package com.stefvar.udacityone;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.stefvar.udacityone.DAO.Movies.FocusMovieDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by steve62742 on 15/7/2016.
 */
public class GridAdapter extends RecyclerView.Adapter {

    private List<FocusMovieDAO> items;
    private Context context;
    private GridAdapterInterface focusmovieAdapterInterface;

    private int count = 10;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CustomViewHolder myview = new CustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.focusmovie, parent, false));
        return myview;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        fill( (CustomViewHolder) holder , items.get(position) );
    }

    @Override
    public int getItemCount() {
        return count;
    }


    public interface GridAdapterInterface {
        void sendViewPosition(int i);
    }


    public GridAdapter(Context context, int height, GridAdapterInterface movies_interface) {
        this.context = context;
        this.focusmovieAdapterInterface = movies_interface;
        items = new ArrayList<>();
    }

    public void addItems(List<FocusMovieDAO> items) {
        this.items.addAll(items);
    }




    private void fill(CustomViewHolder holder, FocusMovieDAO focusMovieDAO) {
        holder.titleTextView.setText(focusMovieDAO.getTitle());
        holder.subtitleTextView.setText(focusMovieDAO.getSubTitle());
        Picasso.with(context)
                .load("https://image.tmdb.org/t/p/w500"+ focusMovieDAO.getDrawable())
                .error(R.drawable.error)
                .placeholder(R.drawable.placeholder)
                .fit()
                //.centerCrop()
                .centerInside()
                .into(holder.image);

    }




    public class CustomViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView titleTextView;
        TextView subtitleTextView;



        public CustomViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.image_custom_item);
            titleTextView = (TextView) v.findViewById(R.id.title_custom_item);
            subtitleTextView = (TextView) v.findViewById(R.id.subtitle_custom_item);



            v.setOnClickListener(mThisButtonListener);
        }


        private View.OnClickListener mThisButtonListener = new View.OnClickListener() {
            public void onClick(View v) {
                int position = getAdapterPosition();
                focusmovieAdapterInterface.sendViewPosition(position);
            }
        };



    }


}
